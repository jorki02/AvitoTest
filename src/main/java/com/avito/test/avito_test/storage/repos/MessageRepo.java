package com.avito.test.avito_test.storage.repos;

import com.avito.test.avito_test.storage.entities.Chat;
import com.avito.test.avito_test.storage.entities.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface MessageRepo extends CrudRepository<Message, Integer> {

    Collection<Message> findAllByChatOrderByCreatedAt(Chat chat);

}
