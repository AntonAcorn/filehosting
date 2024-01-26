package com.acorn.service;

import com.acorn.entity.RoleName;
import com.acorn.model.Account;
import com.acorn.model.TelegramEvent;
import com.acorn.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.User;

@Component
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public Account save (TelegramEvent telegramEvent) {
        User telegramUser = telegramEvent.getUpdate().getMessage().getFrom();
        Account account = Account.builder()
                .firstName(telegramUser.getFirstName())
                .lastName(telegramUser.getLastName())
                .telegramId(String.valueOf(telegramUser.getId()))
                .roleName(RoleName.INACTIVE)
                .build();
        return accountRepository.save(account);
    }
}
