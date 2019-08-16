package com.avito.test.avito_test.service.dto;

import java.util.List;

public class MessagesDto {
    private List<MessageDto> messages;

    private Long count;

    public MessagesDto(List<MessageDto> messages, Long count) {
        this.messages = messages;
        this.count = count;
    }

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
