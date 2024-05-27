package yep.greenFire.greenfirebackend.admin.notice.dto.response;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import yep.greenFire.greenfirebackend.admin.notice.domain.entity.AdminMember;
import yep.greenFire.greenfirebackend.user.member.domain.type.MemberStatus;

import java.util.Date;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AdminMemberResponse {

    private int memberCode;
    private String memberId;
    private String memberName;
    private String memberNickname;
    private String memberEmail;
    private String memberPhone;
    private MemberStatus memberStatus;
    private Date registAt;
    private Date quitAt;

    public AdminMemberResponse(int memberCode, String memberId, String memberName, String memberNickname, String memberEmail, String memberPhone, MemberStatus memberStatus, Date registAt, Date quitAt) {
        this.memberCode=memberCode;
        this.memberId=memberId;
        this.memberName=memberName;
        this.memberNickname=memberNickname;
        this.memberEmail=memberEmail;
        this.memberPhone=memberPhone;
        this.memberStatus=memberStatus;
        this.registAt=registAt;
        this.quitAt=quitAt;
    }

    public static AdminMemberResponse from(final AdminMember adminMember) {
        return new AdminMemberResponse(
                adminMember.getMemberCode(),
                adminMember.getMemberId(),
                adminMember.getMemberName(),
                adminMember.getMemberNickname(),
                adminMember.getMemberEmail(),
                adminMember.getMemberPhone(),
                adminMember.getMemberStatus(),
                adminMember.getRegistAt(),
                adminMember.getQuitAt()
        );
    }
}
