package yep.greenFire.greenfirebackend.report.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yep.greenFire.greenfirebackend.report.dto.response.ReportVO;
import yep.greenFire.greenfirebackend.report.service.ReportMemberService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class ReportMemberController {

    private final ReportMemberService reportMemberService;

    @GetMapping("/suspend/{reportCode}")
    public ResponseEntity<ReportVO> getReportedMember(
            @PathVariable final Long reportCode
    ) {

        final ReportVO reportVO = reportMemberService.getReportsByMember(reportCode);

        return ResponseEntity.ok(reportVO);
    }
}

