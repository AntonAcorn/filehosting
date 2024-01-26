package com.acorn.dao;

import com.acorn.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountDao extends JpaRepository<AccountEntity, Long> {

    AccountEntity getAccountEntitiesByTelegramId(Long telegramId);
}
