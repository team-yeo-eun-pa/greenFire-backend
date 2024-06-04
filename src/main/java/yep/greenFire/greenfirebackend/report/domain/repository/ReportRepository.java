package yep.greenFire.greenfirebackend.report.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import yep.greenFire.greenfirebackend.report.domain.entity.Report;
import yep.greenFire.greenfirebackend.report.dto.response.ReportVO;

import java.util.List;
import java.util.Optional;

public interface ReportRepository extends JpaRepository<Report, Long> {
//    @Query("SELECT r FROM Report r" +
//           " LEFT JOIN r.comment c" +
//           " LEFT JOIN r.review rv" +
//           " WHERE c.memberCode = :memberCode" )
//    List<Report> findByMemberCode(@Param("memberCode") Long memberCode);
//
//    @Query("SELECT COUNT(r) FROM Report r" +
//           " LEFT JOIN r.comment c" +
//           " LEFT JOIN r.review rv" +
//            " WHERE c.memberCode = :memberCode OR rv.reviewWriter = :memberCode")
//    Long findByReportCountByMemberCode(@Param("memberCode")Long memberCode);


    @Query("SELECT new yep.greenFire.greenfirebackend.report.dto.response.ReportVO(r.reportCode, r.reportType, r.reportReason, r.reportDate)" +
            " FROM Report r")
    Optional<ReportVO> getReportCode(Long reportCode);
}
