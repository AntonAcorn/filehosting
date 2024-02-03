package com.acorn.service;

import com.acorn.model.TelegramEvent;

public interface FileService {

    String processDoc(TelegramEvent telegramEvent);
}
