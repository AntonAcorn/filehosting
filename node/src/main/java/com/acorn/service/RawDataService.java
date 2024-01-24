package com.acorn.service;

import com.acorn.mapper.UpdateMapper;
import com.acorn.repository.RawDataRepository;
import com.acorn.schema.RawData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class RawDataService {

    private final RawDataRepository rawDataRepository;
    private final UpdateMapper updateMapper;

    @Transactional
    public RawData save(Update update) {
        var rawDataEntity = updateMapper.convertToSchema(update);
        return rawDataRepository.save(rawDataEntity);
    }
}
