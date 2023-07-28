package com.xi9d.clothcommerce.Account.AccountDTO;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SeedData implements CommandLineRunner {
    private final AccountService accountService;

    @Bean
    private PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public void run(String... args) throws Exception {
        Account account = new Account();
        account.setFirstName("Paul");
        account.setLastName("Webo");
        account.setPassword(passwordEncoder().encode("pass"));
        account.setEmail("paulwebo@gmail.com");
        account.setAddress("Thika, Kenya");
        accountService.addAccount(account);
        System.out.println(account);
    }
}
