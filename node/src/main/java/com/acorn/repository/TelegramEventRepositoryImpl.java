package com.acorn.repository;

import com.acorn.dao.UpdateDao;
import com.acorn.entity.TelegramEventEntity;
import com.acorn.mapper.TelegramEventMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TelegramEventRepositoryImpl implements TelegramEventRepository {

    private final TelegramEventMapper telegramEventMapper;
    private final UpdateDao updateDao;

    @Override
    public com.acorn.model.TelegramEvent save(com.acorn.model.TelegramEvent telegramEvent) {
        var telegramEventEntity = new TelegramEventEntity();
        telegramEventMapper.enrichEntityWithModelsParams(telegramEventEntity, telegramEvent);
        var savedRawDataEntity = updateDao.save(telegramEventEntity);
        return telegramEventMapper.convertToModel(savedRawDataEntity);
    }
}
