package yep.greenFire.greenfirebackend.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import yep.greenFire.greenfirebackend.common.exception.dto.response.ExceptionResponse;

import java.io.IOException;

import static yep.greenFire.greenfirebackend.common.exception.type.ExceptionCode.FAIL_LOGIN;
import static yep.greenFire.greenfirebackend.common.exception.type.ExceptionCode.UNAUTHORIZED;

public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        // 유효한 자격 증명을 제공하지 않고 접근하려는 상황 -> 401
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(new ExceptionResponse(UNAUTHORIZED)));
    }
}
