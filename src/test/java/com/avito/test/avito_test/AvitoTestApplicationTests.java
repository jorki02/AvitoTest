package com.avito.test.avito_test;

import com.avito.test.avito_test.service.ChatService;
import com.avito.test.avito_test.service.dto.ChatDto;
import com.avito.test.avito_test.service.dto.MessageDto;
import com.avito.test.avito_test.storage.entities.Chat;
import com.avito.test.avito_test.storage.entities.ChatUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AvitoTestApplicationTests {

	@Autowired
	ChatService chatService;

	@Test
	public void addUserTest() {
		chatService.addNewUser("Sasha");
		chatService.addNewUser("Masha");
	}


	@Test
	public void addChatTest() {
		Integer id1 = chatService.addNewUser("Sasha");
		Integer id2 = chatService.addNewUser("Masha");

		List<Integer> ids = new ArrayList<>();
		ids.add(id1);
		ids.add(id2);
		Integer idChat = chatService.addNewChat("Chat", ids);

		System.out.println(idChat);
	}

	@Test
	public void addMessageTest() {
		Integer id1 = chatService.addNewUser("Sasha");
		Integer id2 = chatService.addNewUser("Masha");

		List<Integer> ids = new ArrayList<>();
		ids.add(id1);
		ids.add(id2);
		Integer idChat = chatService.addNewChat("Chat", ids);

		Integer messageId = chatService.addMessage(idChat, id1, "Hello World");

		System.out.println(messageId);
	}

	@Test
	public void addLargeMessageTest() {
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

		System.out.println(messageId);
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


		List<MessageDto> chats = chatService.getMessages(idChat1);

		for(MessageDto messageDto : chats){
			System.out.println(messageDto.getText());
		}

		System.out.println(k);

	}

}
