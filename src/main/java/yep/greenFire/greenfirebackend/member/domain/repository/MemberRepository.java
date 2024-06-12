package yep.greenFire.greenfirebackend.member.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import yep.greenFire.greenfirebackend.member.domain.entity.Member;
import yep.greenFire.greenfirebackend.member.domain.type.MemberRole;
import yep.greenFire.greenfirebackend.member.domain.type.MemberStatus;
import yep.greenFire.greenfirebackend.report.dto.response.ReportsVO;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByMemberId(String memberId);

    Optional<Member> findByRefreshToken(String refreshToken);

    Page<Member> findByMemberStatusNot(Pageable pageable, MemberStatus memberStatus);

//    Page<Member> findByMemberStatusIn(Pageable pageable, MemberStatus memberStatus, MemberStatus memberStatus1);

    @Query("SELECT new yep.greenFire.greenfirebackend.report.dto.response.ReportsVO(" +
            "m.memberCode, m.memberId, m.memberName, m.memberNickname, m.memberStatus, " +
            "m.memberRole, m.reportCount, m.suspendedEndDate) " +
            "FROM Member m " +
            "WHERE m.memberStatus IN :statuses AND m.memberRole = :role")
    Page<ReportsVO> findReportVOByMemberStatusInAndRole(
            @Param("statuses") List<MemberStatus> statuses,
            @Param("role") MemberRole role,
            Pageable pageable
    );
    Optional<Member> findByMemberCode(Long memberCode);

}
