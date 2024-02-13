package com.acorn.service;

import com.acorn.enums.LinkType;
import com.acorn.model.TelegramEvent;

public interface FileService {

    String processDoc(TelegramEvent telegramEvent);

    String processPhoto(TelegramEvent telegramEvent);

    String generateLink(Long id, LinkType linkType);
}
