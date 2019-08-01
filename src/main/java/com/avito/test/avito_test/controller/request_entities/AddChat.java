package com.avito.test.avito_test.controller.request_entities;

import java.util.List;

public class AddChat {

    private String name;

    private List<Integer> users;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getUsers() {
        return users;
    }

    public void setUsers(List<Integer> users) {
        this.users = users;
    }
}
