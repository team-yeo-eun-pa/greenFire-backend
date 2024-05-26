package yep.greenFire.greenfirebackend.auth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import yep.greenFire.greenfirebackend.auth.dto.LoginDTO;
import yep.greenFire.greenfirebackend.user.member.service.MemberService;

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
}
