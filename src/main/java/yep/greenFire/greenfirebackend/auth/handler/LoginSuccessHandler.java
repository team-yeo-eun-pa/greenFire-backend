package yep.greenFire.greenfirebackend.auth.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import yep.greenFire.greenfirebackend.auth.service.AuthService;
import yep.greenFire.greenfirebackend.auth.util.TokenUtils;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final AuthService authService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // Filter Chain 없이 Override
        Map<String, Object> memberInfo = getMemberInfo(authentication);
        log.info("로그인 성공 후 인증 객체에서 꺼낸 정보 : {}", memberInfo);

        // create access token, refresh token
        String accessToken = TokenUtils.createAccessToken(memberInfo);
        String refreshToken = TokenUtils.createRefreshToken();
        log.info("accessToken : {}", accessToken);
        log.info("refreshToken : {}", refreshToken);

        // refresh token -> DB
        authService.updateRefreshToken((String)memberInfo.get("memberId"), refreshToken);


        // set token in response header
        response.setHeader("Access-Token", accessToken);
        response.setHeader("Refresh-Token", refreshToken);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    private Map<String, Object> getMemberInfo(Authentication authentication) {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String memberRole = userDetails.getAuthorities()
                .stream().map(auth -> auth.getAuthority().toString())
                .collect(Collectors.joining());

        return Map.of(
                "memberId", userDetails.getUsername(),
                "memberRole", memberRole
        );
    }
}
