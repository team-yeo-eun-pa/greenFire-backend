package yep.greenFire.greenfirebackend.challenge.presentation;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import yep.greenFire.greenfirebackend.challenge.domain.entity.ChallengeContent;
import yep.greenFire.greenfirebackend.challenge.dto.request.ChallengeCreateRequest;
import yep.greenFire.greenfirebackend.challenge.dto.response.challenge.ChallengeResponse;
import yep.greenFire.greenfirebackend.challenge.dto.response.inquiry.InquiryResponse;
import yep.greenFire.greenfirebackend.challenge.service.ChallengeService;
import yep.greenFire.greenfirebackend.common.paging.Pagination;
import yep.greenFire.greenfirebackend.common.paging.PagingButtonInfo;
import yep.greenFire.greenfirebackend.common.paging.PagingResponse;

import java.net.URI;

@RestController
@AllArgsConstructor

public class ChallengeController {

    private ChallengeService challengeService;


   // 판매자 - 챌린지 조회
    @GetMapping("/seller/challenge")
    public ResponseEntity<PagingResponse> challengeView (
            @RequestParam (defaultValue = "1") final Integer page
            ) {
        final Page<ChallengeResponse> challengeResponse = challengeService.getChallengeContent(1L, page);

        final PagingButtonInfo pagingButtonInfo = Pagination.getPagingButtonInfo(challengeResponse);
        final PagingResponse pagingResponse = PagingResponse.of(challengeResponse.getContent(), pagingButtonInfo);

        return ResponseEntity.ok(pagingResponse);


    }


    //판매자 - 챌린지 등록
    @PostMapping("/seller/challenge/regist")
    public ResponseEntity<ChallengeContent> chanllengeRegist (@RequestParam @Valid ChallengeCreateRequest challengeCreateRequest) {

        final ChallengeContent challengeContent = challengeService.save(challengeCreateRequest);
        return ResponseEntity.created(URI.create("/seller/challenge" + challengeContent)).build();

    }
}
