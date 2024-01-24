package com.acorn.dao;

import com.acorn.entity.RawDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface RawDataDao extends JpaRepository<RawDataEntity, Long> {

}
