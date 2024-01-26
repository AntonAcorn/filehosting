package com.acorn.repository;


import com.acorn.entity.RawDataEntity;
import com.acorn.model.RawData;

public interface RawDataRepository {

     RawData save(RawDataEntity rawDataEntity);
}
