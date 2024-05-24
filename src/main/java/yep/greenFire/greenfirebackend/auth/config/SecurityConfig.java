package yep.greenFire.greenfirebackend.auth.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import yep.greenFire.greenfirebackend.auth.service.AuthService;

@RequiredArgsConstructor
@EnableMethodSecurity
@Configuration
public class SecurityConfig {

//    private final PasswordEncoder passwordEncoder;
//    private final AuthService authService;
}
