package com.acorn.dao;

import com.acorn.entity.AppDocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppDocumentDao extends JpaRepository<AppDocumentEntity, Long> {
}
