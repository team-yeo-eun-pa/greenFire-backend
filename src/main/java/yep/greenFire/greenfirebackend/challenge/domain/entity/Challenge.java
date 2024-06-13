package yep.greenFire.greenfirebackend.challenge.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import yep.greenFire.greenfirebackend.challenge.domain.type.ChallengeStatus;

import java.util.Date;

@Entity
@Table(name = "tbl_challenge_info")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Challenge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long challengeCode;
    private String challengeName;

    @Enumerated(value = EnumType.STRING)
    private ChallengeStatus challengeStatus = ChallengeStatus.ACTIVE;

    private String challengeContent;

    @CreatedDate
    private Date challengeStartDate;

    private Date challengeEndDate;
    private Long memberCode;
    private Date challengeModifyDate;
    private Date challengeDeleteDate;

    public Challenge(String challengeName, String challengeContent, Date challengeStartDate, Date challengeEndDate, Long memberCode) {
        this.challengeName=challengeName;
        this.challengeContent=challengeContent;
        this.challengeStartDate=challengeStartDate;
        this.challengeEndDate=challengeEndDate;
        this.memberCode=memberCode;
    }


    public static Challenge of(String challengeName, String challengeContent, Date challengeStartDate, Date challengeEndDate, Long memberCode) {
        return new Challenge(
                challengeName,
                challengeContent,
                challengeStartDate,
                challengeEndDate,
                memberCode

        );
    }
}
