package com.acorn.repository;

import com.acorn.dao.AppDocumentDao;
import com.acorn.entity.AppDocumentEntity;
import com.acorn.entity.BinaryContentEntity;
import com.acorn.mapper.AppDocumentMapper;
import com.acorn.mapper.BinaryContentMapper;
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
    private final BinaryContentMapper binaryContentMapper;

    @Override
    public AppDocument processAndSaveAppDocumentWithFile(TelegramEvent telegramEvent, BinaryContentEntity binaryContent) {
        var appDocumentEntity = new AppDocumentEntity();
        appDocumentEntity.setBinaryContent(binaryContent);
        var document = telegramEvent.getUpdate().getMessage().getDocument();
        var appDocument = constructAppDocumentFromTelegramDoc(document);
        appDocument.setBinaryContent(binaryContentMapper.convertToModel(binaryContent));
        appDocumentMapper.enrichEntityWithModelsParams(appDocumentEntity, appDocument);
        appDocumentDao.save(appDocumentEntity);
        return appDocumentMapper.convertToModel(appDocumentEntity);
    }

    private static AppDocument constructAppDocumentFromTelegramDoc(Document document) {
        return AppDocument.builder()
                .fileId(document.getFileId())
                .fileSize(document.getFileSize())
                .fileName(document.getFileName())
                .mimeType(document.getMimeType())
                .build();
    }
}
