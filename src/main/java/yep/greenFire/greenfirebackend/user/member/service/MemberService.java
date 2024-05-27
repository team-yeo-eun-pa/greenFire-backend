package yep.greenFire.greenfirebackend.user.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yep.greenFire.greenfirebackend.auth.dto.LoginDTO;
import yep.greenFire.greenfirebackend.common.exception.NotFoundException;
import yep.greenFire.greenfirebackend.user.member.domain.entity.Member;
import yep.greenFire.greenfirebackend.user.member.domain.repository.MemberRepository;
import yep.greenFire.greenfirebackend.user.member.dto.request.MemberSignupRequest;

import static yep.greenFire.greenfirebackend.common.exception.type.ExceptionCode.NOT_FOUND_REFRESH_TOKEN;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void signup(MemberSignupRequest memberRequest) {

        final Member newMember = Member.of(
                memberRequest.getMemberId(),
                passwordEncoder.encode(memberRequest.getMemberPassword()),
                memberRequest.getMemberName(),
                memberRequest.getMemberEmail(),
                memberRequest.getMemberPhone(),
                memberRequest.getAddressSido(),
                memberRequest.getAddressSigungu(),
                memberRequest.getAddressDongeupmyeon(),
                memberRequest.getAddressDetail(),
                memberRequest.getAddressZipcode()
        );

        memberRepository.save(newMember);
    }

    @Transactional(readOnly = true)
    public LoginDTO findByMemberId(String memberId) {

        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new UsernameNotFoundException("해당 아이디가 존재하지 않습니다."));

        return LoginDTO.from(member);
    }

    public void updateRefreshToken(String memberId, String refreshToken) {

        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new UsernameNotFoundException("해당 아이디가 존재하지 않습니다."));
        member.updateRefreshToken(refreshToken);
    }

    @Transactional(readOnly = true)
    public LoginDTO findByRefreshToken(String refreshToken) {

        Member member = memberRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_REFRESH_TOKEN));

        return LoginDTO.from(member);
    }
}
