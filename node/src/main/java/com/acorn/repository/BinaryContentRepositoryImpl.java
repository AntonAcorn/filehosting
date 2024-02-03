package com.acorn.repository;

import com.acorn.dao.BinaryContentDao;
import com.acorn.model.BinaryContent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BinaryContentRepositoryImpl implements BinaryContentRepository{

    private final BinaryContentDao binaryContentDao;

    @Override
    public void save(BinaryContent binaryContent) {
        binaryContentDao.save(binaryContent);
    }
}
