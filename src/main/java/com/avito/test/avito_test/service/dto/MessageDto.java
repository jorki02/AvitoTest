package com.avito.test.avito_test.service.dto;

import com.avito.test.avito_test.storage.entities.Chat;
import com.avito.test.avito_test.storage.entities.ChatUser;

import java.util.Date;

public class MessageDto {

    private Integer id;
    private Integer chat;
    private Integer author;
    private String text;
    private Date createdAt;

    public MessageDto() {
    }

    public MessageDto(Integer id, Integer chat, Integer author, String text, Date createdAt) {
        this.id = id;
        this.chat = chat;
        this.author = author;
        this.text = text;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getChat() {
        return chat;
    }

    public void setChat(Integer chat) {
        this.chat = chat;
    }

    public Integer getAuthor() {
        return author;
    }

    public void setAuthor(Integer author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
