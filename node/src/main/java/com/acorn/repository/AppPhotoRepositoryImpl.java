package com.acorn.repository;

import com.acorn.dao.AppPhotoDao;
import com.acorn.entity.AppPhotoEntity;
import com.acorn.entity.BinaryContentEntity;
import com.acorn.mapper.AppPhotoMapper;
import com.acorn.mapper.BinaryContentMapper;
import com.acorn.model.AppPhoto;
import com.acorn.model.TelegramEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;

@Repository
@RequiredArgsConstructor
public class AppPhotoRepositoryImpl implements AppPhotoRepository {

    private final BinaryContentMapper binaryContentMapper;
    private final AppPhotoMapper appPhotoMapper;
    private final AppPhotoDao appPhotoDao;

    @Override
    public AppPhoto getAppPhotoById(String photoId) {
        var appPhotoEntity = appPhotoDao.getById(Long.valueOf(photoId));
        return appPhotoMapper.convertToModel(appPhotoEntity);
    }

    @Override
    public AppPhoto processAndSaveAppPhotoWithFile(TelegramEvent telegramEvent, BinaryContentEntity binaryContent) {
        var appPhotoEntity = new AppPhotoEntity();
        appPhotoEntity.setBinaryContent(binaryContent);
        var photoSizeList = telegramEvent.getUpdate().getMessage().getPhoto();
        var photoSize = photoSizeList.size() > 1 ? photoSizeList.get(photoSizeList.size() - 1) : photoSizeList.get(0);
        var appPhoto = constructAppPhotoFromTelegramPhotoSize(photoSize);
        appPhoto.setBinaryContent(binaryContentMapper.convertToModel(binaryContent));
        appPhotoMapper.enrichEntityWithModelsParams(appPhotoEntity, appPhoto);
        appPhotoDao.save(appPhotoEntity);
        return appPhotoMapper.convertToModel(appPhotoEntity);
    }

    private static AppPhoto constructAppPhotoFromTelegramPhotoSize(PhotoSize photoSize) {
        return AppPhoto.builder()
                .fileId(photoSize.getFileId())
                .fileSize(photoSize.getFileSize())
                .width(photoSize.getWidth())
                .height(photoSize.getHeight())
                .fileUniqueId(photoSize.getFileUniqueId())
                .filePath(photoSize.getFilePath())
                .build();
    }
}