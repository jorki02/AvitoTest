package com.avito.test.avito_test.storage.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class ChatUser {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String username;

    @Column
    private Date createdAt;

    @OneToMany(mappedBy="author",targetEntity=Message.class, fetch=FetchType.EAGER)
    private Set<Message> messages;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }
}
