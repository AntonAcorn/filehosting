package com.acorn.service;

import com.acorn.model.AppPhoto;
import com.acorn.model.TelegramEvent;
import com.acorn.repository.AppPhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppPhotoService {

    private final AppPhotoRepository appPhotoRepository;
    private final BinaryContentService binaryContentService;

    public AppPhoto processAndSaveAppPhotoWithFile(TelegramEvent telegramEvent, byte[] binaryContent) {
        var savedBinaryContent = binaryContentService.save(binaryContent);
        return appPhotoRepository.processAndSaveAppPhotoWithFile(telegramEvent, savedBinaryContent);
    }

    public AppPhoto getAppPhotoById(Long photoId) {
        return appPhotoRepository.getAppPhotoById(photoId);
    }
}
