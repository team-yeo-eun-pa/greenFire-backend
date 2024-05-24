package yep.greenFire.greenfirebackend.user.member.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yep.greenFire.greenfirebackend.user.member.domain.repository.MemberRepository;

import java.lang.reflect.Member;


@Service
public class MemberService {

    private MemberRepository memberRepository;


    @Transactional(readOnly=true)
    public ProfileResponse getProfile(String memberId) {
        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new UsernameNotFoundException("로그인 해주세요"));
        return ProfileResponse.from(member);
    }
}
