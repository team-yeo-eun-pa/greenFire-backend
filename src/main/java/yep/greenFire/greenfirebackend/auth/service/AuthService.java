package yep.greenFire.greenfirebackend.auth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import yep.greenFire.greenfirebackend.auth.dto.LoginDTO;
import yep.greenFire.greenfirebackend.auth.dto.TokenDTO;
import yep.greenFire.greenfirebackend.auth.type.CustomUser;
import yep.greenFire.greenfirebackend.auth.util.TokenUtils;
import yep.greenFire.greenfirebackend.user.member.service.MemberService;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    private final MemberService memberService;

    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {

        log.info("Loading user by username: {}", memberId);
        LoginDTO loginDTO = memberService.findByMemberId(memberId);

        if (loginDTO == null) {
            log.error("User not found: {}", memberId);
            throw new UsernameNotFoundException("User not found: " + memberId);
        }

        log.info("User found: {}", loginDTO);

        return User.builder()
                .username(loginDTO.getMemberId())
                .password(loginDTO.getMemberPassword())
                .roles(loginDTO.getMemberRole().name())
                .build();
    }

    public void updateRefreshToken(String memberId, String refreshToken) {
        memberService.updateRefreshToken(memberId, refreshToken);
    }

    public TokenDTO checkRefreshTokenAndReIssueToken(String refreshToken) {
        LoginDTO loginDTO = memberService.findByRefreshToken(refreshToken);
        // 토큰 재발급
        String reIssueRefreshToken = TokenUtils.createRefreshToken();
        String reIssueAccessToken = TokenUtils.createAccessToken(getMemberInfo(loginDTO));
        memberService.updateRefreshToken(loginDTO.getMemberId(), reIssueRefreshToken);
        return TokenDTO.of(reIssueAccessToken, reIssueRefreshToken);
    }

    private Map<String, Object> getMemberInfo(LoginDTO loginDTO) {
        return Map.of(
                "memberId", loginDTO.getMemberId(),
                "memberRole", loginDTO.getMemberRole()
        );
    }

    public void saveAuthentication(String memberId) {

        LoginDTO loginDTO = memberService.findByMemberId(memberId);

        UserDetails user = User.builder()
                .username(loginDTO.getMemberId())
                .password(loginDTO.getMemberPassword())
                .roles(loginDTO.getMemberRole().name())
                .build();

        CustomUser customUser = new CustomUser(loginDTO.getMemberCode(), user);

        CustomUser customUser = new CustomUser(loginDTO.getMemberCode(), user);

        Authentication authentication
                = new UsernamePasswordAuthenticationToken(customUser, null, customUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}