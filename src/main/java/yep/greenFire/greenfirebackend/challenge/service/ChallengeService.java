package yep.greenFire.greenfirebackend.challenge.service;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import yep.greenFire.greenfirebackend.challenge.domain.entity.ChallengeContent;
import yep.greenFire.greenfirebackend.challenge.domain.repository.ChallengeRepository;
import yep.greenFire.greenfirebackend.challenge.dto.request.ChallengeCreateRequest;

import java.util.Date;

@Service
public class ChallengeService {

    private ChallengeRepository challengeRepository;
    public int save(@RequestParam @Valid ChallengeCreateRequest challengeCreateRequest) {

        final ChallengeContent challengeRegist = ChallengeContent.of(
                challengeCreateRequest.getChallengeCode(),
                challengeCreateRequest.getChallengeName(),
                challengeCreateRequest.getChallengeContent(),
                challengeCreateRequest.getChallengeStartDate(),
                challengeCreateRequest.getChallengeEndDate(),
                challengeCreateRequest.getMemberCode(),
                challengeCreateRequest.getTagCode()
        );

        final ChallengeContent challenge = challengeRepository.save(challengeRegist);

        return challenge.getChallengeCode();
    }
}
