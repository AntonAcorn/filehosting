package com.acorn.service;

import com.acorn.model.TelegramEvent;

public interface FileService {

    void processDoc(TelegramEvent telegramEvent);
}
