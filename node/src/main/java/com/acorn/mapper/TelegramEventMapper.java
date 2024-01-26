package com.acorn.mapper;

import com.acorn.entity.TelegramEventEntity;
import com.acorn.model.TelegramEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TelegramEventMapper extends Mapper<TelegramEvent, TelegramEventEntity> {

    @Override
    public TelegramEvent convertToModel(TelegramEventEntity entity) {
        return TelegramEvent.builder()
                .id(entity.getId())
                .update(entity.getUpdate())
                .build();
    }

    @Override
    public void enrichEntityWithModelsParams(TelegramEventEntity entity, TelegramEvent telegramEvent) {
        entity.setId(telegramEvent.id);
        entity.setUpdate(telegramEvent.update);
    }
}
