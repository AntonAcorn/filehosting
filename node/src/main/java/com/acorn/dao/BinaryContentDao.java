package com.acorn.dao;

import com.acorn.model.BinaryContent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BinaryContentDao extends JpaRepository<BinaryContent, Long> {
}
