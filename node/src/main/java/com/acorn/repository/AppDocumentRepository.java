package com.acorn.repository;

import com.acorn.entity.BinaryContentEntity;
import com.acorn.model.AppDocument;
import com.acorn.model.BinaryContent;
import com.acorn.model.TelegramEvent;

public interface AppDocumentRepository {

    AppDocument processAndSaveAppDocumentWithFile(TelegramEvent telegramEvent, BinaryContentEntity binaryContent);
}
