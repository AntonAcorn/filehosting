package com.acorn.repository;

import com.acorn.entity.AppPhotoEntity;
import com.acorn.entity.BinaryContentEntity;
import com.acorn.model.AppDocument;
import com.acorn.model.AppPhoto;
import com.acorn.model.TelegramEvent;
import org.springframework.stereotype.Repository;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;

@Repository
public class AppPhotoRepositoryImpl implements AppPhotoRepository {

    @Override
    public AppPhoto processAndSaveAppPhotoWithFile(TelegramEvent telegramEvent, BinaryContentEntity binaryContent) {
        var appPhotoEntity = new AppPhotoEntity();
        appPhotoEntity.setBinaryContent(binaryContent);
        var document = telegramEvent.getUpdate().getMessage().getDocument();
        var photoSizeList = telegramEvent.getUpdate().getMessage().getPhoto();
        var photoSize = photoSizeList.size() > 1 ? photoSizeList.get(photoSizeList.size() - 1) : photoSizeList.get(0);
        photoSize.
        var appDocument = constructAppDocumentFromTelegramDoc(document);
        appDocument.setBinaryContent(binaryContentMapper.convertToModel(binaryContent));
        appDocumentMapper.enrichEntityWithModelsParams(appPhotoEntity, appDocument);
        appDocumentDao.save(appPhotoEntity);
        return appDocumentMapper.convertToModel(appPhotoEntity);
    }

    private static AppPhoto constructAppDocumentFromTelegramDoc(PhotoSize photoSize) {
        return AppPhoto.builder()
                .fileId(photoSize.getFileId())
                .fileSize(photoSize.getFileSize())
                .fileName(photoSize.getFileName())
                .mimeType(photoSize.getMimeType())
                .build();
    }
}