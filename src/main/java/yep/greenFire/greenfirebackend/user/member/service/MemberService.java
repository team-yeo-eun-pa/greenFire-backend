package yep.greenFire.greenfirebackend.user.member.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yep.greenFire.greenfirebackend.common.exception.NotFoundException;
import yep.greenFire.greenfirebackend.user.member.domain.repository.MemberRepository;
import yep.greenFire.greenfirebackend.user.member.dto.response.ProfileResponse;

import java.lang.reflect.Member;


@Service
public class MemberService {

    private MemberRepository memberRepository;


    @Transactional(readOnly=true)
    public ProfileResponse getProfile(String memberId) {
        Member member = memberRepository.findByMemberId(memberId);

        if (member == null) {
            throw new NotFoundException(MEMBER_NOT_FOUND);
        }


        // 회원 정보를 ProfileResponse로 변환하여 반환
        return new ProfileResponse(member.getMemberCode(), member.getMemberId(), member.getMemberName());
    }
}


