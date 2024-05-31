package yep.greenFire.greenfirebackend.challenge.presentation;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import yep.greenFire.greenfirebackend.challenge.domain.entity.ChallengeContent;
import yep.greenFire.greenfirebackend.challenge.dto.request.ChallengeCreateRequest;
import yep.greenFire.greenfirebackend.challenge.service.ChallengeService;

import java.net.URI;

@RestController
@AllArgsConstructor

public class ChallengeController {

    private ChallengeService challengeService;
    //판매자 - 챌린지 등록
    @PostMapping("/seller/challenge/regist")
    public ResponseEntity<ChallengeContent> chanllengeRegist (@RequestParam @Valid ChallengeCreateRequest challengeCreateRequest) {

        final ChallengeContent challengeContent = challengeService.save(challengeCreateRequest);
        return ResponseEntity.created(URI.create("/seller/challenge/list" + challengeContent)).build();

    }
}
