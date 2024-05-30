package yep.greenFire.greenfirebackend.admin.report.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yep.greenFire.greenfirebackend.user.member.domain.type.MemberStatus;
import yep.greenFire.greenfirebackend.user.member.service.MemberService;
import yep.greenFire.greenfirebackend.user.report.domain.entity.Report;
import yep.greenFire.greenfirebackend.user.report.domain.repository.ReportRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportMemberService {

    private final ReportRepository reportRepository;
    private final MemberService memberService;

    @Transactional(readOnly = true)
    public List<Report> getReportsByMember(Long memberCode) {
        return reportRepository.findByMemberCode(memberCode);
    }

    @Transactional(readOnly = true)
    public Long getReportCountByMember(Long memberCode) {
        return reportRepository.findByReportCountByMemberCode(memberCode);

    }

    public MemberStatus getMemberStatus(Long memberCode) {
        return memberService.getMemberStatus(memberCode);
    }
}
