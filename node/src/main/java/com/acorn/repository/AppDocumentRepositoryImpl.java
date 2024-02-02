package com.acorn.repository;

import com.acorn.dao.AppDocumentDao;
import com.acorn.entity.AppDocumentEntity;
import com.acorn.mapper.AppDocumentMapper;
import com.acorn.model.AppDocument;
import com.acorn.model.TelegramEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.telegram.telegrambots.meta.api.objects.Document;

@Repository
@RequiredArgsConstructor
public class AppDocumentRepositoryImpl implements AppDocumentRepository{

    private final AppDocumentMapper appDocumentMapper;
    private final AppDocumentDao appDocumentDao;

    @Override
    public AppDocument save(TelegramEvent telegramEvent) {
        var appDocumentEntity = new AppDocumentEntity();
        var document = telegramEvent.getUpdate().getMessage().getDocument();
        var appDocument = constructAppDocumentFromTelegramDocument(document);
        appDocumentMapper.enrichEntityWithModelsParams(appDocumentEntity, appDocument);
        appDocumentDao.save(appDocumentEntity);
        return appDocumentMapper.convertToModel(appDocumentEntity);
    }

    private static AppDocument constructAppDocumentFromTelegramDocument(Document document) {
        return AppDocument.builder()
                .fileId(document.getFileId())
                .fileSize(document.getFileSize())
                .fileName(document.getFileName())
                .mimeType(document.getMimeType())
                .build();
    }
}
