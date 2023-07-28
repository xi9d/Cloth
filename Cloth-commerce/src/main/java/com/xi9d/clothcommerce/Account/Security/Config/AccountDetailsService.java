package com.xi9d.clothcommerce.Account.Security.Config;

import com.xi9d.clothcommerce.Account.AccountDTO.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountDetailsService implements UserDetailsService {
    private final AccountRepository accountRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return  accountRepository.findAccountByEmail(email)
                .orElseThrow( () -> new UsernameNotFoundException("User not found with email "+email));
    }
}
