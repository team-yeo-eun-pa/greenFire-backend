package yep.greenFire.greenfirebackend.challenge.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import yep.greenFire.greenfirebackend.challenge.domain.entity.ChallengeContent;
import yep.greenFire.greenfirebackend.challenge.dto.response.challenge.ChallengeResponse;

public interface ChallengeRepository extends JpaRepository<ChallengeContent, Integer> {
   Page<ChallengeContent> findByChallengeCode(Long challengeCode, Pageable pageable);
}
