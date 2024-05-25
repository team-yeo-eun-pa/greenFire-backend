package yep.greenFire.greenfirebackend.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import yep.greenFire.greenfirebackend.auth.dto.LoginDTO;
import yep.greenFire.greenfirebackend.user.member.service.MemberService;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    private final MemberService memberService;

    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {

        LoginDTO loginDTO = memberService.findByMemberId(memberId);

        return User.builder()
                .username(loginDTO.getMemberId())
                .password(loginDTO.getMemberPassword())
                .roles(loginDTO.getMemberRole().name())
                .build();
    }
}
