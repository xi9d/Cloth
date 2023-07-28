package com.xi9d.clothcommerce.Account.Security.Auth;

import com.xi9d.clothcommerce.Account.AccountDTO.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationResponse {
    private Account account;
    private String token;
}
