package com.avito.test.avito_test.storage.repos;

import com.avito.test.avito_test.storage.entities.Chat;
import com.avito.test.avito_test.storage.entities.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MessageRepo extends PagingAndSortingRepository<Message, Integer> {

    Page<Message> findAllByChatOrderByCreatedAt(Chat chat, Pageable pageable);

}
