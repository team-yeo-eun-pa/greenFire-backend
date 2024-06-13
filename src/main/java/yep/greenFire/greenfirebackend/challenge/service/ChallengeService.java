package yep.greenFire.greenfirebackend.challenge.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yep.greenFire.greenfirebackend.challenge.domain.entity.Challenge;
import yep.greenFire.greenfirebackend.challenge.domain.type.ChallengeStatus;
import yep.greenFire.greenfirebackend.challenge.dto.request.ChallengeCreateRequest;
import yep.greenFire.greenfirebackend.challenge.dto.response.ChallengeResponse;
import yep.greenFire.greenfirebackend.challenge.dto.response.ChallengesResponse;
import yep.greenFire.greenfirebackend.challenge.domain.repository.ChallengeRepository;
import yep.greenFire.greenfirebackend.common.exception.NotFoundException;
import yep.greenFire.greenfirebackend.common.exception.type.ExceptionCode;

@Service
@RequiredArgsConstructor
@Transactional
public class ChallengeService {

    private final ChallengeRepository challengeRepository;



    private Pageable getPageable(final Integer page) {
        return PageRequest.of(page - 1, 10, Sort.by("challengeCode").descending());
    }

    @Transactional(readOnly = true)
    public Page<ChallengesResponse> getChallenges(final Integer page) {
        Page<ChallengesResponse> getChallenges = challengeRepository.getChallenges(getPageable(page), ChallengeStatus.ACTIVE);
        return getChallenges;
    }

    @Transactional(readOnly = true)
    public ChallengeResponse challengeDetail(final Long challengeCode) {

        ChallengeResponse challenge = challengeRepository.challengeDetail(challengeCode, ChallengeStatus.ACTIVE)
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_MEMBER_CODE));

        return challenge;
    }


    public Long save(ChallengeCreateRequest challengeCreateRequest, Long memberCode) {


        final Challenge newChallenge = Challenge.of(
                challengeCreateRequest.getChallengeName(),
                challengeCreateRequest.getChallengeContent(),
                challengeCreateRequest.getChallengeStartDate(),
                challengeCreateRequest.getChallengeEndDate(),
                memberCode
        );

       Challenge challenge = challengeRepository.save(newChallenge);

      return challenge.getChallengeCode();
    }


}
