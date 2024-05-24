package yep.greenFire.greenfirebackend.user.member.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.Member;

@Getter
@RequiredArgsConstructor
public class ProfileResponse {
    private final String memberId;
    private final String memberName;
    private final String memberEmail;

    public static ProfileResponse from(Member member) {
        return new ProfileResponse(
                member.getMemberId(),
                member.getMemberName(),
                member.getMemberEmail()
        );
    }
}
