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
public class ChallengesResponse {

    private Long challengeCode;
    private String challengeName;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private Date challengeStartDate;
    private Long memberCode;
    private String memberName;

    public ChallengesResponse(Long challengeCode, String challengeName,
                              Date challengeStartDate,Long memberCode, String memberName) {
        this.challengeCode = challengeCode;
        this.challengeName = challengeName;
        this.challengeStartDate = challengeStartDate;
        this.memberCode = memberCode;
        this.memberName = memberName;
    }





}
