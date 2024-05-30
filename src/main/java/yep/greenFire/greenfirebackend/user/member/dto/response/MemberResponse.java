package yep.greenFire.greenfirebackend.user.member.dto.response;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import yep.greenFire.greenfirebackend.user.member.domain.entity.Member;
import yep.greenFire.greenfirebackend.user.member.domain.type.MemberStatus;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberResponse {

    private Long memberCode;
    private String memberId;
    private String memberName;
    private String memberNickname;
    private String memberEmail;
    private String memberPhone;
    private MemberStatus memberStatus;
    private LocalDateTime registDate;
    private LocalDateTime quitDate;

    public MemberResponse(Long memberCode, String memberId, String memberName, String memberNickname, String memberEmail, String memberPhone, MemberStatus memberStatus, LocalDateTime registDate, LocalDateTime quitDate) {
        this.memberCode=memberCode;
        this.memberId=memberId;
        this.memberName=memberName;
        this.memberNickname=memberNickname;
        this.memberEmail=memberEmail;
        this.memberPhone=memberPhone;
        this.memberStatus=memberStatus;
        this.registDate=registDate;
        this.quitDate=quitDate;
    }

    public static MemberResponse from(final Member member) {
        return new MemberResponse(
                member.getMemberCode(),
                member.getMemberId(),
                member.getMemberName(),
                member.getMemberNickname(),
                member.getMemberEmail(),
                member.getMemberPhone(),
                member.getMemberStatus(),
                member.getRegistDate(),
                member.getQuitDate()
        );
    }
}
