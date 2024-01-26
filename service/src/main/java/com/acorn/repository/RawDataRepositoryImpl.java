package com.acorn.repository;

import com.acorn.dao.RawDataDao;
import com.acorn.entity.RawDataEntity;
import com.acorn.mapper.RawDataMapper;
import com.acorn.model.RawData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RawDataRepositoryImpl implements RawDataRepository{

    private final RawDataMapper rawDataMapper;
    private final RawDataDao rawDataDao;

    @Override
    public RawData save(RawDataEntity rawDataEntity) {
        var savedRawDataEntity = rawDataDao.save(rawDataEntity);
        return rawDataMapper.convertToSchema(savedRawDataEntity);
    }
}
