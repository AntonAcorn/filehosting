package com.acorn.repository;

import com.acorn.dao.BinaryContentDao;
import com.acorn.entity.BinaryContentEntity;
import com.acorn.mapper.BinaryContentMapper;
import com.acorn.model.BinaryContent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BinaryContentRepositoryImpl implements BinaryContentRepository{

    private final BinaryContentDao binaryContentDao;
    private final BinaryContentMapper binaryContentMapper;

    @Override
    public BinaryContentEntity save(byte[] fileInBytes) {
        var binaryContentEntity = new BinaryContentEntity();
        var binaryContentWithFile = BinaryContent.builder()
                .binaryContent(fileInBytes)
                .build();
        binaryContentMapper.enrichEntityWithModelsParams(binaryContentEntity, binaryContentWithFile);
        binaryContentDao.save(binaryContentEntity);
        return binaryContentEntity;
    }
}
