package yep.greenFire.greenfirebackend.challenge.dto.response.challenge;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import yep.greenFire.greenfirebackend.challenge.domain.entity.ChallengeContent;

import java.util.Date;

@Getter
@RequiredArgsConstructor
public class ChallengeResponse {

    //챌린지 등록 전 보여줄 챌린지 목록

    private int challengeCode;
    private String challengeStatus;
    private String challengeName;
    private String challengeDetail;
    private Date challengeStartDate;
    private Date challengeEndDate;
    private int tagCode;

    public ChallengeResponse(int challengeCode, String challengeStatus, String challengeName, String challengeDetail, Date challengeStartDate, Date challengeEndDate, int tagCode) {
        this.challengeCode = challengeCode;
        this.challengeStatus = challengeStatus;
        this.challengeName = challengeName;
        this.challengeDetail = challengeDetail;
        this.challengeStartDate = challengeStartDate;
        this.challengeEndDate = challengeEndDate;
        this.tagCode = tagCode;
    }


    public ChallengeResponse from(ChallengeContent challengeContent) {
        return new ChallengeResponse(
                challengeContent.getChallengeCode(),
                challengeContent.getChallengeStatus(),
                challengeContent.getChallengeName(),
                challengeContent.getChallengeDetail(),
                challengeContent.getChallengeStartDate(),
                challengeContent.getChallengeEndDate(),
                challengeContent.getTagCode()
        );


    }
}
