package com.acorn.mapper;

import com.acorn.entity.RawDataEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class UpdateMapper {

    public RawDataEntity convertToSchema(Update UpdateEntity) {
        RawDataEntity rawDataEntity = new RawDataEntity();
        rawDataEntity.setUpdate(UpdateEntity);
        return rawDataEntity;
    }
}
