package yep.greenFire.greenfirebackend.admin.report.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yep.greenFire.greenfirebackend.admin.report.service.ReportMemberService;
import yep.greenFire.greenfirebackend.user.member.domain.type.MemberStatus;
import yep.greenFire.greenfirebackend.user.report.domain.entity.Report;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class ReportMemberController {

    private final ReportMemberService reportMemberService;

    @GetMapping("/{memberCode}/report-detail")
    public Map<String, Object> getReportSummaryByMember(@PathVariable("memberCode") Long memberCode) {
        MemberStatus memberStatus = reportMemberService.getMemberStatus(memberCode);

        if(memberStatus == MemberStatus.STOP || memberStatus == MemberStatus.PERMANENTLY_SUSPENDED){
            List<Report> reports = reportMemberService.getReportsByMember(memberCode);
            Long reportCount = reportMemberService.getReportCountByMember(memberCode);

            Map<String, Object> response = new HashMap<>();
            response.put("memberCode", memberCode);
            response.put("reportCount", reportCount);
            response.put("reports",reports);

            return response;
        } else {

            Map<String, Object> response = new HashMap<>();
            response.put("message","정지또는 영구정지 회원이 아님");
            return response;
        }



    }

}
