package yep.greenFire.greenfirebackend.auth.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import yep.greenFire.greenfirebackend.auth.service.AuthService;

@RequiredArgsConstructor
@EnableMethodSecurity
@Configuration
public class SecurityConfig {

    private final PasswordEncoder passwordEncoder;
//    private final AuthService authService;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http.
                /* CSRF 공격 방지는 기본적으로 활성화 되어 있는데 비활성화 처리 */
                        csrf(AbstractHttpConfigurer::disable)
                /* rest api에서는 세션으로 로그인 상태 관리를 하지 않을 예정이므로 STATELESS 설정 */
                .sessionManagement(sessionManage -> sessionManage.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                /* formLogin 비활성화 처리 */
                .formLogin(AbstractHttpConfigurer::disable)
                /* 요청에 대한 권한 체크 */
                .authorizeHttpRequests(auth -> {
                    /* 클라이언트가 외부 도메인을 요청하는 경우 웹 브라우저에서 자체적으로 사전 요청(preflight)이 일어난다.
                     * 이 때 OPTIONS 메소드로 서버에 사전 요청을 보내 확인한다. */
                    auth.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll();
                    auth.requestMatchers(HttpMethod.POST, "/members/signup", "/members/login").permitAll();
                    auth.requestMatchers("/admin/adminNotices").permitAll();
                    auth.requestMatchers("/admin/notices/1").permitAll();
                    auth.anyRequest().authenticated();
                })
//                /* 기본적으로 동작하는 로그인 필터 이전에 커스텀 로그인 필터를 설정한다. */
//                .addFilterBefore(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
//                /* 모든 요청에 대해서 토큰을 확인하는 필터 설정 */
//                .addFilterBefore(jwtAuthenticationFilter(), BasicAuthenticationFilter.class)
//                .exceptionHandling(exceptionHandling -> {
//                    exceptionHandling.accessDeniedHandler(jwtAccessDeniedHandler());
//                    exceptionHandling.authenticationEntryPoint(jwtAuthenticationEntryPoint());
//                })
//                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .build();
    }
}
