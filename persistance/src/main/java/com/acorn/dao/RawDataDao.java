package com.acorn.dao;

import com.acorn.entity.RawDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RawDataDao extends JpaRepository<RawDataEntity, Long> {
}
