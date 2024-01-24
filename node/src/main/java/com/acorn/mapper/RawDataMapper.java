package com.acorn.mapper;

import com.acorn.entity.RawDataEntity;
import com.acorn.schema.RawData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RawDataMapper extends Mapper<RawData, RawDataEntity> {
    @Override
    public RawData convertToSchema(RawDataEntity entity) {
        return RawData.builder()
                .id(entity.getId())
                .update(entity.getUpdate())
                .build();
    }

    @Override
    public void enrichEntityWithSchemaParams(RawDataEntity entity, RawData rawData) {
        entity.setId(rawData.id);
        entity.setUpdate(rawData.update);
    }
}
