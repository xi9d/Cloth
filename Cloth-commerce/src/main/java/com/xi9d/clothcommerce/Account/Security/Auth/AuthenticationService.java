package com.xi9d.clothcommerce.Account.Security.Auth;

import com.xi9d.clothcommerce.Account.AccountDTO.Account;
import com.xi9d.clothcommerce.Account.AccountDTO.AccountRepository;
import com.xi9d.clothcommerce.Account.Security.Config.TokenProvider;
import com.xi9d.clothcommerce.Account.Security.Token.Token;
import com.xi9d.clothcommerce.Account.Security.Token.TokenRepository;
import com.xi9d.clothcommerce.Account.Security.Token.TokenType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final TokenRepository tokenRepository;
    private final AccountRepository accountRepository;
    private final TokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        Account user = accountRepository.findAccountByEmail(request.getEmail()).orElseThrow();
        String jwtToken = tokenProvider.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return new AuthenticationResponse(user, jwtToken);
    }
    private void saveUserToken(Account account, String jwtToken) {
        Token token = Token.builder()
                .account(account)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }
    public void revokeAllUserTokens(Account user) {
        List<Token> validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
}
