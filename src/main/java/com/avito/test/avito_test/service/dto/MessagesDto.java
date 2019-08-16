package com.avito.test.avito_test.service.dto;

import java.util.List;

public class MessagesDto {
    private List<MessageDto> messages;

    private Integer count;

    public MessagesDto(List<MessageDto> messages, Integer count) {
        this.messages = messages;
        this.count = count;
    }

    public List<MessageDto> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageDto> messages) {
        this.messages = messages;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
