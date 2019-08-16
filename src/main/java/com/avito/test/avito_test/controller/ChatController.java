package com.avito.test.avito_test.controller;

import com.avito.test.avito_test.controller.request_entities.*;
import com.avito.test.avito_test.controller.response_entities.ListChats;
import com.avito.test.avito_test.controller.response_entities.ListMessages;
import com.avito.test.avito_test.service.ChatLockServiceProxy;
import com.avito.test.avito_test.service.ChatService;
import com.avito.test.avito_test.service.dto.ChatDto;
import com.avito.test.avito_test.service.dto.MessageDto;
import com.avito.test.avito_test.service.dto.MessagesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ChatController {

    @Autowired
    ChatService chatService;

    @Autowired
    ChatLockServiceProxy chatLockServiceProxy;

    @RequestMapping(value = "/users/add", method = RequestMethod.POST)
    public ResponseEntity<Integer> addUser(@RequestBody AddUser addUser) {
        Integer userId = chatService.addNewUser(addUser.getUsername());
        return new ResponseEntity<>(userId, HttpStatus.OK);
    }

    @RequestMapping(value = "/chats/add", method = RequestMethod.POST)
    public ResponseEntity<Integer> addUser(@RequestBody AddChat addChat) {
        Integer chatId = chatService.addNewChat(addChat.getName(), addChat.getUsers());
        return new ResponseEntity<>(chatId, HttpStatus.OK);
    }

    @RequestMapping(value = "/messages/add", method = RequestMethod.POST)
    public ResponseEntity<Integer> addUser(@RequestBody AddMessage addMessage) {
        Integer messageId = chatLockServiceProxy.addMessage(addMessage.getChat(), addMessage.getAuthor(), addMessage.getText());
        return new ResponseEntity<>(messageId, HttpStatus.OK);
    }

    @RequestMapping(value = "/chats/get", method = RequestMethod.POST)
    public ResponseEntity<ListChats>  addUser(@RequestBody GetChats getChats) {
        List<ChatDto> chatDtoList = chatService.getChats(getChats.getUser());
        ListChats listChats = new ListChats();
        listChats.setChats(chatDtoList);
        return new ResponseEntity<>(listChats, HttpStatus.OK);
    }

    @RequestMapping(value = "/messages/get", method = RequestMethod.POST)
    public ResponseEntity<ListMessages> addUser(@RequestBody GetMessages getMessages) {
        MessagesDto messageDtoList = chatService.getMessages(getMessages.getChat(),
                getMessages.getPageNumber(), getMessages.getPageSize());
        ListMessages listMessages = new ListMessages();
        listMessages.setMessages(messageDtoList.getMessages());
        listMessages.setCount(messageDtoList.getCount());
        return new ResponseEntity<>(listMessages, HttpStatus.OK);
    }

}
