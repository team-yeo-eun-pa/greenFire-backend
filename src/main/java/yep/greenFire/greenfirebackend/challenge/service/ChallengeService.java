package yep.greenFire.greenfirebackend.challenge.service;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import yep.greenFire.greenfirebackend.challenge.domain.entity.ChallengeContent;
import yep.greenFire.greenfirebackend.challenge.domain.repository.ChallengeRepository;
import yep.greenFire.greenfirebackend.challenge.dto.request.ChallengeCreateRequest;
import yep.greenFire.greenfirebackend.challenge.dto.response.challenge.ChallengeResponse;

@Service
public class ChallengeService {

    private ChallengeRepository challengeRepository;

    private Pageable getPageable(final Integer page) {
        return PageRequest.of(page - 1, 10, Sort.by("challengeCode").descending());
    }



    @Transactional(readOnly = true)
    public Page<ChallengeResponse> getChallengeContent(Long challengeCode, Integer page) {

        Page<ChallengeContent> challengeResponse = challengeRepository.findByChallengeCode(challengeCode, getPageable(page));

        return  challengeResponse.map(ChallengeResponse::from);
    }




    public ChallengeContent save(@RequestParam @Valid ChallengeCreateRequest challengeCreateRequest) {


        final ChallengeContent newChallenge = ChallengeContent.of(
                challengeCreateRequest.getChallengeCode(),
                challengeCreateRequest.getChallengeName(),
                challengeCreateRequest.getChallengeContent(),
                challengeCreateRequest.getChallengeStartDate(),
                challengeCreateRequest.getChallengeEndDate(),
                challengeCreateRequest.getMemberCode(),
                challengeCreateRequest.getTagCode()
        );

       challengeRepository.save(newChallenge);

      return newChallenge;
    }


}
