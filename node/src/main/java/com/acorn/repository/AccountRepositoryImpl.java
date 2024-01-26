package com.acorn.repository;

import com.acorn.dao.AccountDao;
import com.acorn.entity.AccountEntity;
import com.acorn.mapper.AccountMapper;
import com.acorn.model.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AccountRepositoryImpl implements AccountRepository{

    private final AccountMapper accountMapper;
    private final AccountDao accountDao;

    @Override
    public Account save(Account account) {
        var accountEntity = AccountEntity.createWithDefaultParams();
        accountMapper.enrichEntityWithModelsParams(accountEntity, account);
        var savedAccountEntity = accountDao.save(accountEntity);
        return accountMapper.convertToModel(savedAccountEntity);
    }
}
