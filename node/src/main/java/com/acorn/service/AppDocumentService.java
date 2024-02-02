package com.acorn.service;

import com.acorn.model.AppDocument;
import com.acorn.model.TelegramEvent;
import com.acorn.repository.AppDocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppDocumentService {

    private final AppDocumentRepository appDocumentRepository;

    public AppDocument save(TelegramEvent telegramEvent) {
        return appDocumentRepository.save(telegramEvent);
    }
}
