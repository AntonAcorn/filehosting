package com.acorn.repository;

import com.acorn.model.Account;

import java.util.Optional;

public interface AccountRepository {

    Account save(Account account);

    Account getOneByTelegramId(Long telegramId);
}
