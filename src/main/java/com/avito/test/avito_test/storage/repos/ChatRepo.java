package com.avito.test.avito_test.storage.repos;

import com.avito.test.avito_test.storage.entities.Chat;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface ChatRepo extends CrudRepository<Chat, Integer> {

    @Query("FROM Chat c JOIN c.users cu LEFT JOIN c.messages msg WHERE cu.id = ?1 ORDER BY msg.createdAt DESC")
    Collection<Chat> findAllChatsByUserIdSorted(Integer userId);

}
