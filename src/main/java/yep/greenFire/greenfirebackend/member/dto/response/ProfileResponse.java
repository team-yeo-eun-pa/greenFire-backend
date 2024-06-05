package yep.greenFire.greenfirebackend.member.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import yep.greenFire.greenfirebackend.member.domain.entity.Member;

@Getter
@RequiredArgsConstructor
public class ProfileResponse {

    private final String memberId;
    private final String memberName;
    private final String memberNickname;
    private final String memberEmail;
    private final String memberPhone;

    // 주소

    public static ProfileResponse from(Member member) {

        return new ProfileResponse(
                member.getMemberId(),
                member.getMemberName(),
                member.getMemberNickname(),
                member.getMemberEmail(),
                member.getMemberPhone()
        );
    }

}
