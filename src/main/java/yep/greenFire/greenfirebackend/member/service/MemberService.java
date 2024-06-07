package yep.greenFire.greenfirebackend.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yep.greenFire.greenfirebackend.auth.dto.LoginDTO;
import yep.greenFire.greenfirebackend.common.exception.NotFoundException;
import yep.greenFire.greenfirebackend.common.exception.type.ExceptionCode;
import yep.greenFire.greenfirebackend.member.domain.entity.Member;
import yep.greenFire.greenfirebackend.member.domain.repository.MemberRepository;
import yep.greenFire.greenfirebackend.member.dto.request.MemberSignupRequest;
import yep.greenFire.greenfirebackend.member.domain.type.MemberStatus;
import yep.greenFire.greenfirebackend.member.dto.request.ProfileUpdateRequest;
import yep.greenFire.greenfirebackend.member.dto.response.ProfileResponse;

import static yep.greenFire.greenfirebackend.common.exception.type.ExceptionCode.NOT_FOUND_MEMBER_CODE;
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
                memberRequest.getMemberNickname(),
                memberRequest.getMemberEmail(),
                memberRequest.getMemberPhone()
        );

        memberRepository.save(newMember);
    }

    @Transactional(readOnly = true)
    public LoginDTO findByMemberId(String memberId) {

        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_MEMBER_ID));

        return LoginDTO.from(member);
    }

    public void updateRefreshToken(String memberId, String refreshToken) {

        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_MEMBER_ID));

        member.updateRefreshToken(refreshToken);
    }

    @Transactional(readOnly = true)
    public LoginDTO findByRefreshToken(String refreshToken) {

        System.out.println("refreshToken : " + refreshToken);

        Member member = memberRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_REFRESH_TOKEN));

        return LoginDTO.from(member);
    }

    @Transactional(readOnly = true)
    public ProfileResponse getProfile(String memberId) {

        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_MEMBER_ID));

        return ProfileResponse.from(member);
    }

    public void modifyProfile(String memberId, ProfileUpdateRequest profileRequest, Long memberCode) {

        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_MEMBER_ID));

        if (!member.getMemberCode().equals(memberCode)) {
            throw new NotFoundException(ExceptionCode.ACCESS_DENIED);
        }

        member.modifyProfile(
                memberCode,
                profileRequest.getMemberNickname(),
                profileRequest.getMemberEmail(),
                profileRequest.getMemberPhone()
        );

    }

    @Transactional(readOnly = true)
    public MemberStatus getMemberStatus(Long memberCode) {
        Member member = memberRepository.findByMemberCode(memberCode)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_MEMBER_CODE));

        return member.getMemberStatus();
    }

    public void suspensionEnd(Long memberCode) {
        Member member = memberRepository.findByMemberCode(memberCode)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_MEMBER_CODE));
        member.suspensionEnd();
    }
}
