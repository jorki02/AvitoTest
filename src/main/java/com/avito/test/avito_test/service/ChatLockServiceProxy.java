package com.avito.test.avito_test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class ChatLockServiceProxy {

    @Autowired
    ChatService chatService;

    final private ConcurrentMap<Integer, Lock> lockConcurrentMap = new ConcurrentHashMap<>();

    public Integer addMessage(Integer chatId, Integer userId, String text){
        Lock lock;
        synchronized (lockConcurrentMap) {
            lock = lockConcurrentMap.getOrDefault(chatId, new ReentrantLock());
            lockConcurrentMap.putIfAbsent(chatId, lock);
        }
        lock.lock();

        Integer id = chatService.addMessage(chatId, userId, text);

        lock.unlock();

        return id;
    }

}
