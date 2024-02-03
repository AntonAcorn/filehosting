package com.acorn.repository;

import com.acorn.entity.BinaryContentEntity;

public interface BinaryContentRepository {

    BinaryContentEntity save(byte[] fileInBytes);
}
