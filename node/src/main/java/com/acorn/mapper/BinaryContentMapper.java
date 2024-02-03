package com.acorn.mapper;

import com.acorn.entity.BinaryContentEntity;
import com.acorn.model.BinaryContent;
import org.springframework.stereotype.Component;

@Component
public class BinaryContentMapper extends Mapper<BinaryContent, BinaryContentEntity> {

    @Override
    public BinaryContent convertToModel(BinaryContentEntity entity) {
        return BinaryContent.builder()
                .binaryContent(entity.getBinaryContent())
                .build();
    }

    @Override
    public void enrichEntityWithModelsParams(BinaryContentEntity entity, BinaryContent binaryContent) {
        entity.setBinaryContent(binaryContent.getBinaryContent());
    }
}
