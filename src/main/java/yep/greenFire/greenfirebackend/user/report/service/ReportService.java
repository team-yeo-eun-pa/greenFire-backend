package yep.greenFire.greenfirebackend.user.report.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yep.greenFire.greenfirebackend.user.member.domain.entity.Member;
import yep.greenFire.greenfirebackend.user.member.domain.repository.MemberRepository;
import yep.greenFire.greenfirebackend.user.member.domain.type.MemberStatus;
import yep.greenFire.greenfirebackend.user.report.domain.entity.Report;
import yep.greenFire.greenfirebackend.user.report.domain.repository.ReportRepository;
import yep.greenFire.greenfirebackend.user.report.dto.request.ReportCreateRequest;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void createReport(ReportCreateRequest reportRequest) {
        Report report = new Report();
        report.setReportDate(LocalDateTime.now());
        report.setReportReason(reportRequest.getReportReason());
        report.setReportType(reportRequest.getReportType());
        Member reportedMember = memberRepository.findById(reportRequest.getMemberCode())
                .orElseThrow(() -> new RuntimeException("Member Not Found"));

                report.setMemberCode(reportedMember.getMemberCode());

        reportRepository.save(report);

        reportedMember.increaseReportCount(reportedMember.getReportCount() + 1);

        applySuspensionRules(reportedMember);

    }

    private void applySuspensionRules(Member member) {
        Long reportCount = member.getReportCount();

        if(reportCount >= 15) {
            member.setMemberStatus(MemberStatus.PERMANENTLY_SUSPENDED);
            member.setSuspendedEndDate(null);
        }else if (reportCount >= 10) {
            member.setMemberStatus(MemberStatus.STOP);
            member.setSuspendedEndDate(LocalDateTime.now().plusWeeks(2));
        } else if (reportCount >= 5) {
            member.setMemberStatus(MemberStatus.STOP);
            member.setSuspendedEndDate(LocalDateTime.now().plusWeeks(1));
        }
        memberRepository.save(member);
    }
}
