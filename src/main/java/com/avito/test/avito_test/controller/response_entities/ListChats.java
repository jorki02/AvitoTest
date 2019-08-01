package com.avito.test.avito_test.controller.response_entities;

import com.avito.test.avito_test.service.dto.ChatDto;

import java.util.List;

public class ListChats {

    private List<ChatDto> chats;

    public List<ChatDto> getChats() {
        return chats;
    }

    public void setChats(List<ChatDto> chats) {
        this.chats = chats;
    }
}
