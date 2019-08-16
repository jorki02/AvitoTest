package com.avito.test.avito_test;

import com.avito.test.avito_test.service.ChatService;
import com.avito.test.avito_test.service.dto.ChatDto;
import com.avito.test.avito_test.service.dto.MessageDto;
import com.avito.test.avito_test.service.dto.MessagesDto;
import com.avito.test.avito_test.storage.entities.Chat;
import com.avito.test.avito_test.storage.entities.ChatUser;
import com.avito.test.avito_test.storage.repos.ChatRepo;
import com.avito.test.avito_test.storage.repos.ChatUserRepo;
import com.avito.test.avito_test.storage.repos.MessageRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:database-test.properties")
@SpringBootTest
public class AvitoTestApplicationTests {

	@Autowired
	ChatService chatService;

	@Autowired
	ChatUserRepo chatUserRepo;

	@Autowired
	ChatRepo chatRepo;

	@Autowired
	MessageRepo messageRepo;

	@Test
	public void addUserTest() {
		Long countUsers = chatUserRepo.count();

		chatService.addNewUser("Sasha");
		chatService.addNewUser("Masha");

		assertEquals(countUsers + 2, chatUserRepo.count());
	}


	@Test
	public void addChatTest() {
		Long countChats = chatRepo.count();

		Integer id1 = chatService.addNewUser("Sasha");
		Integer id2 = chatService.addNewUser("Masha");

		List<Integer> ids = new ArrayList<>();
		ids.add(id1);
		ids.add(id2);
		Integer idChat = chatService.addNewChat("Chat", ids);

		assertEquals(countChats + 1, chatRepo.count());
	}

	@Test
	public void addMessageTest() {
		Long messageCount = messageRepo.count();

		Integer id1 = chatService.addNewUser("Sasha");
		Integer id2 = chatService.addNewUser("Masha");

		List<Integer> ids = new ArrayList<>();
		ids.add(id1);
		ids.add(id2);
		Integer idChat = chatService.addNewChat("Chat", ids);

		Integer messageId = chatService.addMessage(idChat, id1, "Hello World");

		assertEquals(messageCount + 1, messageRepo.count());
	}

	@Test
	public void addLargeMessageTest() {
		Long messageCount = messageRepo.count();

		Integer id1 = chatService.addNewUser("Sasha");
		Integer id2 = chatService.addNewUser("Masha");

		List<Integer> ids = new ArrayList<>();
		ids.add(id1);
		ids.add(id2);
		Integer idChat = chatService.addNewChat("Chat", ids);

		Integer messageId = chatService.addMessage(idChat, id1, "Hello WorldHello WorldHello WorldHello Wor" +
				"ldHello WorldHello WorldHello WorldHello WorldHello World Hello WorldHello WorldHello WorldHello Wor" +
				"Hello WorldHello WorldHello WorldHello WorHello WorldHello WorldHello WorldHello Wor" +
				"Hello WorldHello WorldHello WorldHello WorHello WorldHello WorldHello WorldHello Wor" +
				"Hello WorldHello WorldHello WorldHello WorHello WorldHello WorldHello WorldHello Wor" +
				"Hello WorldHello WorldHello WorldHello Wor" +
				"Hello WorldHello WorldHello WorldHello Wor" +
				"Hello WorldHello WorldHello WorldHello Wor" +
				"Hello WorldHello WorldHello WorldHello Wor" +
				"Hello WorldHello WorldHello WorldHello Wor" +
				"Hello WorldHello WorldHello WorldHello Wor" +
				"Hello WorldHello WorldHello WorldHello Wor" +
				"Hello WorldHello WorldHello WorldHello Wor" +
				"Hello WorldHello WorldHello WorldHello Wor" +
				"Hello WorldHello WorldHello WorldHello Wor" +
				"Hello WorldHello WorldHello WorldHello Wor" +
				"Hello WorldHello WorldHello WorldHello Wor" +
				"Hello WorldHello WorldHello WorldHello Wor" +
				"Hello WorldHello WorldHello WorldHello Wor" +
				"Hello WorldHello WorldHello WorldHello Wor" +
				"Hello WorldHello WorldHello WorldHello Wor" +
				"Hello WorldHello WorldHello WorldHello Wor");

		assertEquals(messageCount + 1, messageRepo.count());
	}

	@Test
	public void getChatsTest() {
		Integer id1 = chatService.addNewUser("Sasha");
		Integer id2 = chatService.addNewUser("Masha");
		Integer id3 = chatService.addNewUser("Jeka");

		List<Integer> ids = new ArrayList<>();
		ids.add(id1);
		ids.add(id2);
		Integer idChat1 = chatService.addNewChat("Chat1", ids);

		ids = new ArrayList<>();
		ids.add(id1);
		ids.add(id3);
		Integer idChat2 = chatService.addNewChat("Chat2", ids);

		chatService.addMessage(idChat2, id1, "Hello World2");
		int k = 1;
		for(int i = 0; i < 10000000; i++){
			k++;
		}

		chatService.addMessage(idChat1, id1, "Hello World");
		k = 1;
		for(int i = 0; i < 10000000; i++){
			k++;
		}
		chatService.addMessage(idChat1, id2, "Hello World1");

		List<ChatDto> chats = chatService.getChats(id1);

		for(ChatDto chatDto : chats){
			System.out.println(chatDto.getName());
		}

		System.out.println(k);

	}

	@Test
	public void getChatsWithoutMessagesTest() {
		Integer id1 = chatService.addNewUser("Sasha");
		Integer id2 = chatService.addNewUser("Masha");
		Integer id3 = chatService.addNewUser("Jeka");

		List<Integer> ids = new ArrayList<>();
		ids.add(id1);
		ids.add(id2);
		Integer idChat1 = chatService.addNewChat("Chat1", ids);

		ids = new ArrayList<>();
		ids.add(id1);
		ids.add(id3);
		Integer idChat2 = chatService.addNewChat("Chat2", ids);

		List<ChatDto> chats = chatService.getChats(id1);

		for(ChatDto chatDto : chats){
			for(Integer userId : chatDto.getUsers()){
				System.out.println(userId);
			}
			System.out.println(chatDto.getName());
		}

	}

	@Test
	public void getMessagesTest() {
		Integer id1 = chatService.addNewUser("Sasha");
		Integer id2 = chatService.addNewUser("Masha");
		Integer id3 = chatService.addNewUser("Jeka");

		List<Integer> ids = new ArrayList<>();
		ids.add(id1);
		ids.add(id2);
		Integer idChat1 = chatService.addNewChat("Chat1", ids);

		ids = new ArrayList<>();
		ids.add(id1);
		ids.add(id3);
		Integer idChat2 = chatService.addNewChat("Chat2", ids);

		chatService.addMessage(idChat2, id1, "Hello World2");
		int k = 1;
		for(int i = 0; i < 10000000; i++){
			k++;
		}

		chatService.addMessage(idChat1, id2, "Hello World1");
		k = 1;
		for(int i = 0; i < 10000000; i++){
			k++;
		}
		chatService.addMessage(idChat1, id1, "Hello World");


		MessagesDto chats = chatService.getMessages(idChat1, 0, 5);

		for(MessageDto messageDto : chats.getMessages()){
			System.out.println(messageDto.getText());
		}

		System.out.println(chats.getCount());

	}

}
