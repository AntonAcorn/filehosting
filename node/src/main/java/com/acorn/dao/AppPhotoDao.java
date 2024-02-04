package com.acorn.dao;

import com.acorn.entity.AppPhotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppPhotoDao extends JpaRepository<AppPhotoEntity, Long> {
}
