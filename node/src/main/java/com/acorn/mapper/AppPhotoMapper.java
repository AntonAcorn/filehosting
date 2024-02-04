package com.acorn.mapper;

import com.acorn.entity.AppPhotoEntity;
import com.acorn.model.AppPhoto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppPhotoMapper extends Mapper<AppPhoto, AppPhotoEntity> {

    private final BinaryContentMapper binaryContentMapper;

    @Override
    public AppPhoto convertToModel(AppPhotoEntity entity) {
        return AppPhoto.builder()
                .fileId(entity.getFileId())
                .fileName(entity.getFileName())
                .fileSize(entity.getFileSize())
                .mimeType(entity.getMimeType())
                .binaryContent(binaryContentMapper.convertToModel(entity.getBinaryContent()))
                .build();
    }

    @Override
    public void enrichEntityWithModelsParams(AppPhotoEntity entity, AppPhoto appPhoto) {
        entity.setId(appPhoto.getId());
        entity.setFileId(appPhoto.getFileId());
        entity.setFileName(appPhoto.getFileName());
        entity.setFileSize(appPhoto.getFileSize());
        entity.setMimeType(appPhoto.getMimeType());
        //TODO: BinaryContent is added in AppDocumentRepository. How to improve?
    }
}
