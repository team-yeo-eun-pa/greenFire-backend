package yep.greenFire.greenfirebackend.member.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import yep.greenFire.greenfirebackend.member.domain.entity.Member;
import yep.greenFire.greenfirebackend.member.domain.type.MemberStatus;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByMemberId(String memberId);

    Optional<Member> findByRefreshToken(String refreshToken);

    Page<Member> findByMemberStatusNot(Pageable pageable, MemberStatus memberStatus);

//    Page<Member> findByMemberStatusIn(Pageable pageable, MemberStatus memberStatus, MemberStatus memberStatus1);

    @Query("SELECT m FROM Member m WHERE m.memberStatus IN :statuses")
    Page<Member> findByMemberStatusIn(List<MemberStatus> statuses, Pageable pageable);

    Optional<Member> findByMemberCode(Long memberCode);
}
