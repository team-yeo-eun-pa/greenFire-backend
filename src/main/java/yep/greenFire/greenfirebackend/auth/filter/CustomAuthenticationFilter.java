package yep.greenFire.greenfirebackend.auth.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class CustomAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    @Autowired
    private ObjectMapper objectMapper;
    public CustomAuthenticationFilter() {
        super(new AntPathRequestMatcher("/members/login", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

        // request content type check
        if(request.getContentType() == null || !request.getContentType().equals("application/json")) {
            throw new AuthenticationServiceException("Content-Type not supported");
        }

        // Request Body 읽어오기
        String body = StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8);

        // JSON -> Java Map
        Map<String, String> bodyMap = objectMapper.readValue(body, Map.class);

        String memberId = bodyMap.get("memberId");
        String memberPassword = bodyMap.get("memberPassword");

        // AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(memberId, memberPassword);

        // AuthenticationToken 전달
        return this.getAuthenticationManager().authenticate(authenticationToken);

    }
}
