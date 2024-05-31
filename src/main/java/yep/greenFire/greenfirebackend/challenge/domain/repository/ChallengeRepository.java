package yep.greenFire.greenfirebackend.challenge.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yep.greenFire.greenfirebackend.challenge.domain.entity.ChallengeContent;

public interface ChallengeRepository extends JpaRepository<ChallengeContent, Integer> {
}
