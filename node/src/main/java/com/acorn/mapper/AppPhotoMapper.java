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
                .id(entity.getId())
                .fileId(entity.getFileId())
                .fileUniqueId(entity.getFileUniqueId())
                .width(entity.getWidth())
                .height(entity.getHeight())
                .fileSize(entity.getFileSize())
                .filePath(entity.getFilePath())
                .binaryContent(binaryContentMapper.convertToModel(entity.getBinaryContent()))
                .build();
    }

    @Override
    public void enrichEntityWithModelsParams(AppPhotoEntity entity, AppPhoto appPhoto) {
        entity.setId(appPhoto.getId());
        entity.setFileId(appPhoto.getFileId());
        entity.setFileUniqueId(appPhoto.getFileUniqueId());
        entity.setWidth(appPhoto.getWidth());
        entity.setHeight(appPhoto.getHeight());
        entity.setFileSize(appPhoto.getFileSize());
        entity.setFilePath(appPhoto.getFilePath());
        //TODO: BinaryContent is added in AppDocumentRepository. How to improve?
    }
}
