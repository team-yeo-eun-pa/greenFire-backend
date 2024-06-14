package yep.greenFire.greenfirebackend.challenge.presentation;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import yep.greenFire.greenfirebackend.auth.type.CustomUser;
import yep.greenFire.greenfirebackend.challenge.dto.request.ChallengeCreateRequest;
import yep.greenFire.greenfirebackend.challenge.dto.response.ChallengeResponse;
import yep.greenFire.greenfirebackend.challenge.dto.response.ChallengesResponse;
import yep.greenFire.greenfirebackend.challenge.service.ChallengeService;
import yep.greenFire.greenfirebackend.common.paging.Pagination;
import yep.greenFire.greenfirebackend.common.paging.PagingButtonInfo;
import yep.greenFire.greenfirebackend.common.paging.PagingResponse;

@RestController
@AllArgsConstructor
@RequestMapping("/")
public class ChallengeController {

    private ChallengeService challengeService;


   // 챌린지 조회
    @GetMapping("/challenges")
    public ResponseEntity<PagingResponse> challengeView (
            @RequestParam (defaultValue = "1") final Integer page
            ) {
        final Page<ChallengesResponse> challenges = challengeService.getChallenges(page);
        final PagingButtonInfo pagingButtonInfo = Pagination.getPagingButtonInfo(challenges);
        final PagingResponse pagingResponse = PagingResponse.of(challenges.getContent(), pagingButtonInfo);

        return ResponseEntity.ok(pagingResponse);

    }

    @GetMapping("/challenges/{challengeCode}")
    public ResponseEntity<ChallengeResponse> challengeDetail (
         @PathVariable final Long challengeCode
    ) {
        final ChallengeResponse challengeResponse = challengeService.challengeDetail(challengeCode);
        return ResponseEntity.ok(challengeResponse);
    }


    // 챌린지 등록
    @PostMapping("/challenges/regist")
    public ResponseEntity<Void> save (@RequestParam @Valid ChallengeCreateRequest challengeCreateRequest,
                                      @AuthenticationPrincipal final CustomUser customUser) {

        challengeService.save(challengeCreateRequest, customUser.getMemberCode());

        return ResponseEntity.status(HttpStatus.CREATED).build();

    }
}
