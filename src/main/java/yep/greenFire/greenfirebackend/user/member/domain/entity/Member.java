package yep.greenFire.greenfirebackend.user.member.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@Table(name="tbl_member")
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int memberCode;
    private String memberId;
    private String memberPassword;
    private String memberName;
    private String memberNickname;
    private String memberEmail;
    private String memberPhone;
    private String memberStatus;
    private String memberRole;
    private Date registAt;
    private Date quitAt;
    private String snsType;
    private String refreshToken;
    private int reportCount;
    private String adressSido;
    private String adressSigungu;
    private String adressDongeupmyeon;
    private String adressDetail;
    private String adressZipcode;

    public Member(int memberCode, String memberId, String memberPassword, String memberName, String memberNickname, String memberEmail, String memberPhone, String memberStatus, String memberRole, Date registAt, Date quitAt, String snsType, String refreshToken, int reportCount, String adressSido, String adressSigungu, String adressDongeupmyeon, String adressDetail, String adressZipcode) {
        this.memberCode = memberCode;
        this.memberId = memberId;
        this.memberPassword = memberPassword;
        this.memberName = memberName;
        this.memberNickname = memberNickname;
        this.memberEmail = memberEmail;
        this.memberPhone = memberPhone;
        this.memberStatus = memberStatus;
        this.memberRole = memberRole;
        this.registAt = registAt;
        this.quitAt = quitAt;
        this.snsType = snsType;
        this.refreshToken = refreshToken;
        this.reportCount = reportCount;
        this.adressSido = adressSido;
        this.adressSigungu = adressSigungu;
        this.adressDongeupmyeon = adressDongeupmyeon;
        this.adressDetail = adressDetail;
        this.adressZipcode = adressZipcode;
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
