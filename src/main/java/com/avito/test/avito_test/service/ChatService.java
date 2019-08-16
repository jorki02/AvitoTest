package com.avito.test.avito_test.service;


import com.avito.test.avito_test.service.dto.ChatDto;
import com.avito.test.avito_test.service.dto.MessageDto;
import com.avito.test.avito_test.service.dto.MessagesDto;
import com.avito.test.avito_test.storage.entities.Chat;
import com.avito.test.avito_test.storage.entities.ChatUser;
import com.avito.test.avito_test.storage.entities.Message;
import com.avito.test.avito_test.storage.repos.ChatRepo;
import com.avito.test.avito_test.storage.repos.ChatUserRepo;
import com.avito.test.avito_test.storage.repos.MessageRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Transactional
@Service
public class ChatService {

    @Autowired
    ChatUserRepo chatUserRepo;
    @Autowired
    ChatRepo chatRepo;
    @Autowired
    MessageRepo messageRepo;
    @Autowired
    ModelMapper modelMapper;

    final private ConcurrentMap<Integer, Lock> lockConcurrentMap = new ConcurrentHashMap<>();

    public Integer addNewUser(String username){
        ChatUser chatUser = new ChatUser();
        chatUser.setUsername(username);
        chatUser.setCreatedAt(new Date());

        chatUser = chatUserRepo.save(chatUser);

        return chatUser.getId();
    }

    public Integer addNewChat(String name, List<Integer> users){
        Chat chat = new Chat();
        chat.setName(name);
        chat.setCreatedAt(new Date());

        Set<ChatUser> chatUsers = chat.getUsers();

        for(Integer user : users){
            Optional<ChatUser> chatUserById = chatUserRepo.findById(user);
            if(chatUserById.isPresent()){
                ChatUser chatUser = chatUserById.get();
                chatUsers.add(chatUser);
            }
        }

        chat = chatRepo.save(chat);

        return chat.getId();
    }

    public Integer addMessage(Integer chatId, Integer userId, String text){
        Lock lock;
        synchronized (lockConcurrentMap) {
            lock = lockConcurrentMap.getOrDefault(chatId, new ReentrantLock());
            lockConcurrentMap.putIfAbsent(chatId, lock);
        }
        lock.lock();
        Message message = new Message();
        message.setText(text);
        message.setCreatedAt(new Date());

        Optional<ChatUser> userById = chatUserRepo.findById(userId);
        if(userById.isPresent()){
            ChatUser chatUser = userById.get();
            message.setAuthor(chatUser);
        }
        Optional<Chat> chatById = chatRepo.findById(chatId);
        if(chatById.isPresent()){
            Chat chat = chatById.get();
            chat.setCount(chat.getCount() + 1);
            message.setChat(chat);
        }

        message = messageRepo.save(message);

        lock.unlock();

        return message.getId();
    }

    public List<ChatDto> getChats(Integer userId){
        Collection<Chat> chatEntities = chatRepo.findAllChatsByUserIdSorted(userId);

        List<ChatDto> chatDtos = new ArrayList<>();
        for(Chat chat : chatEntities){
            ChatDto chatDto = modelMapper.map(chat, ChatDto.class);
            List<Integer> chatUserIds = new ArrayList<>();
            for(ChatUser chatUser : chat.getUsers()){
                chatUserIds.add(chatUser.getId());
            }
            chatDto.setUsers(chatUserIds);
            chatDtos.add(chatDto);
        }

        return chatDtos;
    }

    public MessagesDto getMessages(Integer chatId, Integer pageNumber, Integer pageSize){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Optional<Chat> chat = chatRepo.findById(chatId);

        Page<Message> messageEntities;
        Integer count;
        if(chat.isPresent()){
            count = chat.get().getCount();
            messageEntities = messageRepo.findAllByChatOrderByCreatedAt(chat.get(), pageable);
        } else {
            throw new RuntimeException("Chat with given id doesn't exists");
        }

        List<MessageDto> messageDtos = new ArrayList<>();
        for(Message message : messageEntities){
            MessageDto messageDto = modelMapper.map(message, MessageDto.class);
            messageDto.setAuthor(message.getAuthor().getId());
            messageDto.setChat(message.getChat().getId());
            messageDtos.add(messageDto);
        }

        return new MessagesDto(messageDtos, count);
    }

}
