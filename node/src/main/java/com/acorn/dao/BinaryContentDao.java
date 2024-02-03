package com.acorn.dao;

import com.acorn.entity.BinaryContentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BinaryContentDao extends JpaRepository<BinaryContentEntity, Long> {
}
