package yep.greenFire.greenfirebackend.admin.member.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import yep.greenFire.greenfirebackend.admin.member.domain.entity.AdminMember;
import yep.greenFire.greenfirebackend.user.member.domain.type.MemberStatus;

public interface AdminMemberRepository extends JpaRepository<AdminMember, Long> {
    Page<AdminMember> findByMemberStatusNot(Pageable pageable, MemberStatus memberStatus);


}