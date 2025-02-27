package com.andreluiscardoso.digitalwallet.infrastructure.securiry;

import com.andreluiscardoso.digitalwallet.domain.model.User;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<Token> login(@RequestBody @Valid Login login) {
        var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(login.email(), login.password());
        var authentication = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        return ResponseEntity.ok(this.tokenService.generateToken((User) authentication.getPrincipal()));
    }
}
