package com.acorn.repository;

import com.acorn.entity.BinaryContentEntity;
import com.acorn.model.AppPhoto;
import com.acorn.model.TelegramEvent;

public interface AppPhotoRepository {

    AppPhoto processAndSaveAppPhotoWithFile(TelegramEvent telegramEvent, BinaryContentEntity binaryContent);

    AppPhoto getAppPhotoById(Long photoId);
}
