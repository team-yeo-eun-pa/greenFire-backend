package yep.greenFire.greenfirebackend.challenge.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import yep.greenFire.greenfirebackend.challenge.domain.entity.Challenge;
import yep.greenFire.greenfirebackend.challenge.domain.type.ChallengeStatus;
import yep.greenFire.greenfirebackend.challenge.dto.response.ChallengeResponse;
import yep.greenFire.greenfirebackend.challenge.dto.response.ChallengesResponse;

import java.util.Optional;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {

   @Query(
           value = " SELECT" +
                   " new yep.greenFire.greenfirebackend.challenge.dto.response.ChallengesResponse(" +
                   " CH.challengeCode, CH.challengeName, CH.challengeStartDate,CH.memberCode, M.memberName)" +
                   " FROM " +
                   " Challenge CH" +
                   " LEFT JOIN " +
                   " Member M" +
                   " ON" +
                   " M.memberCode = CH.memberCode" +
                   " WHERE " +
                   " CH.challengeStatus = :challengeStatus"
   )
   Page<ChallengesResponse> getChallenges(Pageable pageable, @Param("challengeStatus")ChallengeStatus challengeStatus);


   @Query(
           value = " SELECT" +
                   " new yep.greenFire.greenfirebackend.challenge.dto.response.ChallengeResponse(" +
                   " CH.challengeCode, CH.challengeName, CH.challengeContent, CH.challengeStartDate, CH.challengeEndDate, CH.memberCode, M.memberName)" +
                   " FROM " +
                   " Challenge CH" +
                   " LEFT JOIN " +
                   " Member M" +
                   " ON" +
                   " M.memberCode = CH.memberCode" +
                   " WHERE " +
                   " CH.challengeStatus = :challengeStatus" +
                   " AND " +
                   " CH.challengeCode = :challengeCode"
   )
   Optional<ChallengeResponse> challengeDetail(@Param("challengeCode")Long challengeCode, @Param("challengeStatus")ChallengeStatus challengeStatus);
}
