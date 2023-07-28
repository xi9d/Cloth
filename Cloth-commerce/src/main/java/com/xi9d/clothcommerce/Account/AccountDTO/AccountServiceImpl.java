package com.xi9d.clothcommerce.Account.AccountDTO;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{
    private final AccountRepository accountRepository;
    @Override
    public void addAccount(Account account) {
        accountRepository.save(account);
    }
}
