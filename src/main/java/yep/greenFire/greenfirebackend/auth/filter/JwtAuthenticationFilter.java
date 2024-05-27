package yep.greenFire.greenfirebackend.auth.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;
import yep.greenFire.greenfirebackend.auth.dto.TokenDTO;
import yep.greenFire.greenfirebackend.auth.service.AuthService;
import yep.greenFire.greenfirebackend.auth.util.TokenUtils;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final AuthService authService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 1. 사용자 헤더에서 refresh token 추출
        String refreshToken = TokenUtils.getToken(request.getHeader("Refresh-Token"));

        // 2. refresh token 있는 상황 : DB에서 일치 여부 확인 후 재발급, 응답
        if(refreshToken != null && TokenUtils.isValidToken(refreshToken)) {
            TokenDTO token = authService.checkRefreshTokenAndReIssueToken(refreshToken);
            response.setHeader("Access-Token", token.getAccessToken());
            response.setHeader("Refresh-Token", token.getRefreshToken());
            return;
        }

        // 3. refresh token 없는 상황 : Access Token 추출 후 유효성 확인, 저장
        String accessToken = TokenUtils.getToken(request.getHeader("Access-Token"));
        if (accessToken != null && TokenUtils.isValidToken(accessToken)) {
            String memberId = TokenUtils.getMemberId(accessToken);
            authService.saveAuthentication(memberId);
        }

        // access token 전달한 경우,
        // token 없이 요청이 발생한 경우
        // 다음 필터로 진행
        filterChain.doFilter(request, response);

    }
}
