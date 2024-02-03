package com.acorn.repository;

import com.acorn.dao.BinaryContentDao;
import com.acorn.entity.BinaryContentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BinaryContentRepositoryImpl implements BinaryContentRepository{

    private final BinaryContentDao binaryContentDao;

    @Override
    public void save(BinaryContentEntity binaryContent) {
        binaryContentDao.save(binaryContent);
    }
}
