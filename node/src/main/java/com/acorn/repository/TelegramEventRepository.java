package com.acorn.repository;


import com.acorn.model.TelegramEvent;

public interface TelegramEventRepository {

     TelegramEvent save(TelegramEvent telegramEvent);
}
