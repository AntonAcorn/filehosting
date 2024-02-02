package com.acorn.repository;

import com.acorn.model.AppDocument;
import com.acorn.model.TelegramEvent;

public interface AppDocumentRepository {

    AppDocument save(TelegramEvent telegramEvent);
}
