package yep.greenFire.greenfirebackend.report.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yep.greenFire.greenfirebackend.common.exception.NotFoundException;
import yep.greenFire.greenfirebackend.common.exception.type.ExceptionCode;
import yep.greenFire.greenfirebackend.report.domain.entity.Report;
import yep.greenFire.greenfirebackend.report.domain.repository.ReportRepository;
import yep.greenFire.greenfirebackend.member.domain.type.MemberStatus;
import yep.greenFire.greenfirebackend.member.service.MemberService;
import yep.greenFire.greenfirebackend.report.dto.response.ReportVO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportMemberService {

    private final ReportRepository reportRepository;
    private final MemberService memberService;

    public ReportVO getReportsByMember(final Long reportCode) {

        ReportVO reportVO = reportRepository.getReportCode(reportCode).orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_REPORT_CODE));

        return reportVO;
    }

//    @Transactional(readOnly = true)
//    public List<Report> getReportsByMember(Long memberCode) {
//        return reportRepository.findByMemberCode(memberCode);
//    }
//
//    @Transactional(readOnly = true)
//    public Long getReportCountByMember(Long memberCode) {
//        return reportRepository.findByReportCountByMemberCode(memberCode);
//
//    }
//
//    public MemberStatus getMemberStatus(Long memberCode) {
//        return memberService.getMemberStatus(memberCode);
//    }
}
