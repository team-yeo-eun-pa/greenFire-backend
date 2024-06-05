package yep.greenFire.greenfirebackend.challenge.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@Table(name = "tbl_challenge")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class ChallengeContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int challengeCode;
    private String challengeName;
    private String challengeStatus;
    private String challengeContent;
    private Date challengeStartDate;
    private Date challengeEndDate;
    private int memberCode;
    private int tagCode;
    private Date challengeModifyDate;
    private Date challengeDeleteDate;


    public ChallengeContent(int challengeCode, String challengeName, String challengeStatus, String challengeContent, Date challengeStartDate, Date challengeEndDate, int memberCode, int tagCode, Date challengeModifyDate, Date challengeDeleteDate) {
        this.challengeCode = challengeCode;
        this.challengeName = challengeName;
        this.challengeStatus = challengeStatus;
        this.challengeContent = challengeContent;
        this.challengeStartDate = challengeStartDate;
        this.challengeEndDate = challengeEndDate;
        this.memberCode = memberCode;
        this.tagCode = tagCode;
        this.challengeModifyDate = challengeModifyDate;
        this.challengeDeleteDate = challengeDeleteDate;
    }


    public static ChallengeContent of(
            final int challengeCode, final String challengeName, final String challengeContent, final Date challengeStartDate,
            final Date challengeEndDate, final int memberCode, final int tagCode) {
        return new ChallengeContent();
    }

    public ChallengeContent save(ChallengeContent challengeRegist) {
        return null;
    }
}
