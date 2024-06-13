package yep.greenFire.greenfirebackend.challenge.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class ChallengeResponse {

    private Long challengeCode;
    private String challengeName;
    private String challengeContent;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private Date challengeStartDate;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private Date challengeEndDate;
    private Long memberCode;
    private String memberName;


    public ChallengeResponse(Long challengeCode, String challengeName, String challengeContent, Date challengeStartDate, Date challengeEndDate, Long memberCode, String memberName) {
        this.challengeCode = challengeCode;
        this.challengeName = challengeName;
        this.challengeContent = challengeContent;
        this.challengeStartDate = challengeStartDate;
        this.challengeEndDate = challengeEndDate;
        this.memberCode = memberCode;
        this.memberName = memberName;
    }
}



