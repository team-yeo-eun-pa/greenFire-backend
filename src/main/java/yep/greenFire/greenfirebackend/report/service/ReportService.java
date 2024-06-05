package yep.greenFire.greenfirebackend.report.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yep.greenFire.greenfirebackend.report.domain.entity.Report;
import yep.greenFire.greenfirebackend.report.domain.repository.ReportRepository;
import yep.greenFire.greenfirebackend.member.domain.entity.Member;
import yep.greenFire.greenfirebackend.member.domain.repository.MemberRepository;
import yep.greenFire.greenfirebackend.member.domain.type.MemberStatus;
import yep.greenFire.greenfirebackend.report.dto.request.ReportCreateRequest;
import yep.greenFire.greenfirebackend.store.domain.entity.Store;
import yep.greenFire.greenfirebackend.store.domain.repository.StoreRepository;
import yep.greenFire.greenfirebackend.store.domain.type.StoreStatus;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public void createMemberReport(ReportCreateRequest reportRequest) {
        Report report = new Report();
        report.setReportDate(LocalDateTime.now());
        report.setReportReason(reportRequest.getReportReason());
        report.setReportType(reportRequest.getReportType());

        if (reportRequest.getMemberCode() > 0) {
            Member reportedMember = memberRepository.findById(reportRequest.getMemberCode())
                    .orElseThrow(() -> new RuntimeException("Member Not Found"));
            report.setMemberCode(reportedMember.getMemberCode());
            reportedMember.increaseReportCount(reportedMember.getReportCount() == null ? 1 : reportedMember.getReportCount() + 1);
            applySuspensionRules(reportedMember);
        } else if (reportRequest.getStoreCode() > 0) {
            Store reportedStore = storeRepository.findById(reportRequest.getStoreCode())
                    .orElseThrow(() -> new RuntimeException("Store Not Found"));
            report.setStoreCode(reportedStore.getStoreCode());
            reportedStore.increaseStoreReportCount(reportedStore.getReportCount() == null ? 1 : reportedStore.getReportCount() + 1);
            applySuspensionRules2(reportedStore);
        }

        reportRepository.save(report);
    }

    private void applySuspensionRules(Member member) {
        Long reportCount = member.getReportCount() == null ? 0 : member.getReportCount();

        if (reportCount >= 15) {
            member.setMemberStatus(MemberStatus.PERMANENTLY_SUSPENDED);
            member.setSuspendedEndDate(null);
        } else if (reportCount >= 10) {
            member.setMemberStatus(MemberStatus.STOP);
            member.setSuspendedEndDate(LocalDateTime.now().plusWeeks(2));
        } else if (reportCount >= 5) {
            member.setMemberStatus(MemberStatus.STOP);
            member.setSuspendedEndDate(LocalDateTime.now().plusWeeks(1));
        }
        memberRepository.save(member);
    }

    private void applySuspensionRules2(Store store) {
        Long reportCount = store.getReportCount() == null ? 0 : store.getReportCount();

        if (reportCount >= 15) {
            store.setStoreStatus(StoreStatus.PERMANENTLY_SUSPENDED);
            store.setSuspendedEndDate(null);
        } else if (reportCount >= 10) {
            store.setStoreStatus(StoreStatus.SUSPENDED);
            store.setSuspendedEndDate(LocalDateTime.now().plusMonths(3));
        } else if (reportCount >= 5) {
            store.setStoreStatus(StoreStatus.SUSPENDED);
            store.setSuspendedEndDate(LocalDateTime.now().plusMonths(6));
        }
        storeRepository.save(store);
    }
}
