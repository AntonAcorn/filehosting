package com.acorn.dao;

import com.acorn.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountDao extends JpaRepository<AccountEntity, Long> {

    Optional<AccountEntity> findAccountEntitiesByTelegramId(Long telegramId);
}
