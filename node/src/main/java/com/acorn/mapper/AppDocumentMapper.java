package com.acorn.mapper;

import com.acorn.entity.AppDocumentEntity;
import com.acorn.model.AppDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppDocumentMapper extends Mapper<AppDocument, AppDocumentEntity> {

    private final BinaryContentMapper binaryContentMapper;

    @Override
    public AppDocument convertToModel(AppDocumentEntity entity) {
        return AppDocument.builder()
                .fileId(entity.getFileId())
                .fileName(entity.getFileName())
                .fileSize(entity.getFileSize())
                .mimeType(entity.getMimeType())
                .binaryContent(binaryContentMapper.convertToModel(entity.getBinaryContent()))
                .build();
    }

    @Override
    public void enrichEntityWithModelsParams(AppDocumentEntity entity, AppDocument appDocument) {
        entity.setId(appDocument.getId());
        entity.setFileId(appDocument.getFileId());
        entity.setFileName(appDocument.getFileName());
        entity.setFileSize(appDocument.getFileSize());
        entity.setMimeType(appDocument.getMimeType());
        //TODO: BinaryContent is added in AppDocumentRepository. How to improve?
    }
}
