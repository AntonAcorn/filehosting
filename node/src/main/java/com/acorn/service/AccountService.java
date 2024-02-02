package com.acorn.service;

import com.acorn.entity.AccountState;
import com.acorn.model.Account;
import com.acorn.model.TelegramEvent;
import com.acorn.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public Account create(TelegramEvent telegramEvent) {
        var telegramUser = telegramEvent.getUpdate().getMessage().getFrom();
        Account account = Account.builder()
                .firstName(telegramUser.getFirstName())
                .lastName(telegramUser.getLastName())
                .telegramId(telegramUser.getId())
                .isActive(false)
                .accountState(AccountState.BASIC_STATE)
                .build();
        return accountRepository.save(account);
    }

    public Account findOrCreate(TelegramEvent telegramEvent) {
        var telegramId = telegramEvent.getUpdate().getMessage().getFrom().getId();
        var persistentAccount = accountRepository.getOneByTelegramId(telegramId);
        if (persistentAccount == null) {
          return create(telegramEvent);
       }
        return persistentAccount;
    }
}
