package yep.greenFire.greenfirebackend.admin.report.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yep.greenFire.greenfirebackend.admin.member.domain.entity.AdminMember;
import yep.greenFire.greenfirebackend.admin.member.domain.repository.AdminMemberRepository;
import yep.greenFire.greenfirebackend.admin.member.domain.type.AdminMemberStatus;
import yep.greenFire.greenfirebackend.admin.report.domain.entity.Report;
import yep.greenFire.greenfirebackend.admin.report.domain.repository.ReportRepository;
import yep.greenFire.greenfirebackend.admin.report.dto.request.ReportCreateRequest;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;
    private final AdminMemberRepository adminMemberRepository;

    @Transactional
    public void createReport(ReportCreateRequest reportRequest) {
        Report report = new Report();
        report.setReportDate(LocalDateTime.now());
        report.setReportReason(reportRequest.getReportReason());
        report.setReportType(reportRequest.getReportType());
        AdminMember reportedMember = adminMemberRepository.findById(reportRequest.getMemberCode())
                .orElseThrow(() -> new RuntimeException("Member Not Found"));

                report.setMemberCode(reportedMember.getMemberCode());

        reportRepository.save(report);

        reportedMember.increaseReportCount(reportedMember.getReportCount() + 1);

        applySuspensionRules(reportedMember);

    }

    private void applySuspensionRules(AdminMember member) {
        Long reportCount = member.getReportCount();

        if(reportCount >= 15) {
            member.setMemberStatus(AdminMemberStatus.PERMANENTLY_SUSPENDED);
            member.setSuspendedEndDate(null);
        }else if (reportCount >= 10) {
            member.setMemberStatus(AdminMemberStatus.STOP);
            member.setSuspendedEndDate(LocalDateTime.now().plusWeeks(2));
        } else if (reportCount >= 5) {
            member.setMemberStatus(AdminMemberStatus.STOP);
            member.setSuspendedEndDate(LocalDateTime.now().plusWeeks(1));
        }
        adminMemberRepository.save(member);
    }
}
