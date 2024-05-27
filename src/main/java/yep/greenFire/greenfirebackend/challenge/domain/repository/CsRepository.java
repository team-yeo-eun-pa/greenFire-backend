package yep.greenFire.greenfirebackend.challenge.domain.repository;

import java.util.Optional;

public interface CsRepository {
    Optional<Object> findByMemberId(String memberId);
}
