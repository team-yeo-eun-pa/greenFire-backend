package yep.greenFire.greenfirebackend.user.member.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import yep.greenFire.greenfirebackend.admin.member.domain.entity.AdminMember;
import yep.greenFire.greenfirebackend.user.member.domain.entity.Member;
import yep.greenFire.greenfirebackend.user.member.domain.type.MemberStatus;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByMemberId(String memberId);

    Optional<Member> findByRefreshToken(String refreshToken);

    Page<Member> findByMemberStatusNot(Pageable pageable, MemberStatus memberStatus);

    Page<Member> findByMemberStatus(Pageable pageable, MemberStatus memberStatus, MemberStatus memberStatus1);
}
