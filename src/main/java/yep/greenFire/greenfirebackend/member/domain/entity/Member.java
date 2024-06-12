package yep.greenFire.greenfirebackend.member.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import yep.greenFire.greenfirebackend.member.domain.type.MemberRole;
import yep.greenFire.greenfirebackend.member.domain.type.MemberStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_member")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberCode;
    private String memberId;
    private String memberPassword;
    private String memberNickname;
    private String memberName;
    private String memberEmail;
    private String memberPhone;
    @Enumerated(value = EnumType.STRING)
    private MemberStatus memberStatus = MemberStatus.ACTIVE;
    @Enumerated(value = EnumType.STRING)
    private MemberRole memberRole = MemberRole.MEMBER;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @CreatedDate
    private LocalDateTime registDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime quitDate;
    private String refreshToken;
    private Long reportCount;
    private LocalDateTime suspendedEndDate;


    private Member(String memberId, String memberPassword, String memberName, String memberNickname, String memberEmail, String memberPhone, MemberStatus memberStatus) {
        this.memberId = memberId;
        this.memberPassword = memberPassword;
        this.memberName = memberName;
        this.memberNickname = memberNickname == null || memberNickname.trim().isEmpty() ? memberId : memberNickname;
        this.memberEmail = memberEmail;
        this.memberPhone = memberPhone;
        this.memberStatus = memberStatus;
    }

    public static Member of(String memberId, String memberPassword, String memberName, String memberNickname, String memberEmail, String memberPhone, MemberStatus memberStatus) {
        return new Member(
                memberId,
                memberPassword,
                memberName,
                memberNickname,
                memberEmail,
                memberPhone,
                memberStatus
        );
    }

    public void activate() {
        this.memberStatus = MemberStatus.ACTIVE;
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }


    public void increaseReportCount(Long reportCount) {
        this.reportCount= reportCount;
    }

    public void suspensionEnd() {
        if (this.memberStatus == MemberStatus.STOP || this.memberStatus == MemberStatus.PERMANENTLY_SUSPENDED){
            this.memberStatus = MemberStatus.ACTIVE;
            this.suspendedEndDate = null;
        }
    }

    public void modifyProfile(Long memberCode, String memberNickname, String memberEmail, String memberPhone) {
        this.memberCode = memberCode;
        this.memberNickname = memberNickname;
        this.memberEmail = memberEmail;
        this.memberPhone = memberPhone;
    }
}
