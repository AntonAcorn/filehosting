package com.acorn.service;

import com.acorn.entity.BinaryContentEntity;
import com.acorn.model.AppDocument;
import com.acorn.model.BinaryContent;
import com.acorn.model.TelegramEvent;
import com.acorn.repository.AppDocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppDocumentService {

    private final AppDocumentRepository appDocumentRepository;
    private final BinaryContentService binaryContentService;

    public AppDocument processAndSaveAppDocumentWithFile(TelegramEvent telegramEvent, byte[] binaryContent) {
        var savedBinaryContent = binaryContentService.save(binaryContent);
        return appDocumentRepository.processAndSaveAppDocumentWithFile(telegramEvent, savedBinaryContent);
    }
}
