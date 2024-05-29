package yep.greenFire.greenfirebackend.user.member.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import yep.greenFire.greenfirebackend.user.member.domain.type.MemberRole;
import yep.greenFire.greenfirebackend.user.member.domain.type.MemberStatus;

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
    private String memberNickname;
    private String memberEmail;
    private String memberPhone;
    @Enumerated(value = EnumType.STRING)
    private MemberStatus memberStatus = MemberStatus.ACTIVE;
    @Enumerated(value = EnumType.STRING)
    private MemberRole memberRole = MemberRole.MEMBER;
    @CreatedDate
    private LocalDateTime registAt;
    @LastModifiedDate
    private LocalDateTime quitAt;
    private String refreshToken;
    private String addressSido;
    private String addressSigungu;
    private String addressDongeupmyeon;
    private String addressDetail;
    private String addressZipcode;



    private Member(String memberId, String memberPassword, String memberName, String memberNickname, String memberEmail, String memberPhone, String addressSido, String addressSigungu, String addressDongeupmyeon, String addressDetail, String addressZipcode) {
        this.memberId = memberId;
        this.memberPassword = memberPassword;
        this.memberName = memberName;
        this.memberNickname = memberNickname;
        this.memberEmail = memberEmail;
        this.memberPhone = memberPhone;
        this.addressSido = addressSido;
        this.addressSigungu = addressSigungu;
        this.addressDongeupmyeon = addressDongeupmyeon;
        this.addressDetail = addressDetail;
        this.addressZipcode = addressZipcode;
    }

    public static Member of(String memberId, String memberPassword, String memberName, String memberNickname, String memberEmail, String memberPhone, String addressSido, String addressSigungu, String addressDongeupmyeon, String addressDetail, String addressZipcode) {
        return new Member(
                memberId,
                memberPassword,
                memberName,
                memberNickname,
                memberEmail,
                memberPhone,
                addressSido,
                addressSigungu,
                addressDongeupmyeon,
                addressDetail,
                addressZipcode
        );
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    private Long reportCount;

    public void increaseReportCount(Long reportCount) {
        this.reportCount= reportCount;
    }

    private LocalDateTime suspendedEndDate;

}
