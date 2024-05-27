package yep.greenFire.greenfirebackend.challenge.domain.repository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CsRepository {
    Optional<Object> findByMemberId(String memberId);

    Object findByCsCode(int csCode);
}
