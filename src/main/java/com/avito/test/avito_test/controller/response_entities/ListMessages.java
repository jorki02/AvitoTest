package com.avito.test.avito_test.controller.response_entities;

import com.avito.test.avito_test.service.dto.MessageDto;
import com.avito.test.avito_test.storage.entities.Message;

import java.util.List;

public class ListMessages {

    private List<MessageDto> messages;
    private Long count;

    public List<MessageDto> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageDto> messages) {
        this.messages = messages;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
