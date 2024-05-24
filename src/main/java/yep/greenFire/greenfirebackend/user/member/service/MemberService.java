package yep.greenFire.greenfirebackend.user.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yep.greenFire.greenfirebackend.user.member.domain.entity.Member;
import yep.greenFire.greenfirebackend.user.member.domain.repository.MemberRepository;
import yep.greenFire.greenfirebackend.user.member.dto.request.MemberSignupRequest;

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
}
