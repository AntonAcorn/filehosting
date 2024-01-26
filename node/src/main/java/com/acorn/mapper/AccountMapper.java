package com.acorn.mapper;

import com.acorn.entity.AccountEntity;
import com.acorn.model.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper extends Mapper<Account, AccountEntity> {

    @Override
    public Account convertToModel(AccountEntity entity) {
        return Account.builder()
                .telegramId(entity.getTelegramId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .dateCreated(entity.getDateCreated())
                .isDeleted(entity.isDeleted())
                .roleName(entity.getRoleName())
                .build();
    }

    @Override
    public void enrichEntityWithModelsParams(AccountEntity entity, Account account) {
        entity.setId(account.getId());
        entity.setTelegramId(account.getTelegramId());
        entity.setFirstName(account.getFirstName());
        entity.setLastName(account.getLastName());
        entity.setEmail(account.getEmail());
        entity.setDeleted(account.isDeleted());
    }
}
