package yep.greenFire.greenfirebackend.report.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import yep.greenFire.greenfirebackend.member.domain.type.MemberRole;
import yep.greenFire.greenfirebackend.member.domain.type.MemberStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ReportsVO {


    private Long memberCode;

    private String memberId;

    private String memberName;

    private String memberNickname;

    private MemberStatus memberStatus;

    private MemberRole memberRole;

    private Long reportCount;

    private LocalDateTime suspendedEndDate;

    public ReportsVO(Long memberCode, String memberId, String memberName, String memberNickname, MemberStatus memberStatus, MemberRole memberRole, Long reportCount, LocalDateTime suspendedEndDate) {
        this.memberCode = memberCode;
        this.memberId=memberId;
        this.memberName = memberName;
        this.memberNickname = memberNickname;
        this.memberStatus = memberStatus;
        this.memberRole = memberRole;
        this.reportCount = reportCount;
        this.suspendedEndDate = suspendedEndDate;
    }
}
