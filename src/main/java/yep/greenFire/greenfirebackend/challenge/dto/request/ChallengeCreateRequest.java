package yep.greenFire.greenfirebackend.challenge.dto.request;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Getter
@RequiredArgsConstructor
public class ChallengeCreateRequest {
    //챌린지 등록시 입력해야 할 사항들

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int challengeCode;

    @NotBlank
    private String challengeName;

    @NotBlank
    private String challengeContent;

    private Date challengeStartDate;

    private Date challengeEndDate;

    private int memberCode;

    private int tagCode;
    //등록하는 판매자 코드
}
