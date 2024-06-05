package yep.greenFire.greenfirebackend.auth.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import yep.greenFire.greenfirebackend.member.domain.entity.Member;
import yep.greenFire.greenfirebackend.member.domain.type.MemberRole;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginDTO {

    private final Long memberCode;
    private final String memberId;
    private final String memberPassword;
    private final MemberRole memberRole;

    public static LoginDTO from(Member member) {
        return new LoginDTO(
                member.getMemberCode(),
                member.getMemberId(),
                member.getMemberPassword(),
                member.getMemberRole()
                // 탈퇴일
        );
    }
}
