package yep.greenFire.greenfirebackend.user.member.domain.repository;

import java.util.Optional;

public interface MemberRepository {
    Optional<Object> findByMemberId(String memberId);
}
