package com.acorn.dao;

import com.acorn.entity.TelegramEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UpdateDao extends JpaRepository<TelegramEventEntity, Long> {
}
