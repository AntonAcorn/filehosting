package com.acorn.service;

import com.acorn.entity.RoleName;
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
                .roleName(RoleName.INACTIVE)
                .build();
        return accountRepository.save(account);
    }

    public void findOrCreate(TelegramEvent telegramEvent) {
        var telegramId = telegramEvent.getUpdate().getMessage().getFrom().getId();
        var persistentAccount = accountRepository.getByTelegramId(telegramId);
        if (persistentAccount == null) {
            create(telegramEvent);
       }
    }
}
