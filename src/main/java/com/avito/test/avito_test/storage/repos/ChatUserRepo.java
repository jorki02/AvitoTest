package com.avito.test.avito_test.storage.repos;

import com.avito.test.avito_test.storage.entities.ChatUser;
import org.springframework.data.repository.CrudRepository;

public interface ChatUserRepo extends CrudRepository<ChatUser, Integer> {
}
