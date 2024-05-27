package yep.greenFire.greenfirebackend.admin.notice.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import yep.greenFire.greenfirebackend.admin.notice.domain.entity.AdminMember;
import yep.greenFire.greenfirebackend.admin.notice.dto.response.AdminNoticesResponse;
import yep.greenFire.greenfirebackend.user.member.domain.type.MemberStatus;

public interface AdminMemberRepository extends JpaRepository<AdminMember, Long> {
    Page<AdminMember> findByMemberStatusNot(Pageable pageable, MemberStatus memberStatus);


}