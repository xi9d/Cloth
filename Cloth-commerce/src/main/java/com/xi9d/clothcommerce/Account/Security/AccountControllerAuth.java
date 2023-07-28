package com.xi9d.clothcommerce.Account.Security;

import com.xi9d.clothcommerce.Account.Security.Auth.AuthenticationRequest;
import com.xi9d.clothcommerce.Account.Security.Auth.AuthenticationResponse;
import com.xi9d.clothcommerce.Account.Security.Auth.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AccountControllerAuth {
    private final AuthenticationService authenticationService;
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request
    ) throws DataIntegrityViolationException {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
