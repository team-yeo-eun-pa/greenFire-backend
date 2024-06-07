package yep.greenFire.greenfirebackend.report.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yep.greenFire.greenfirebackend.report.dto.response.ReportVO;
import yep.greenFire.greenfirebackend.report.service.ReportMemberService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class ReportMemberController {

    private final ReportMemberService reportMemberService;

    @GetMapping("/suspend/{memberId}")
    public ResponseEntity<List<ReportVO>> getReportedMember(
            @PathVariable final String memberId
    ) {
        System.out.println("memberId : "+memberId);

        final List<ReportVO> reportVO = reportMemberService.getReportsByMember(memberId);

        return ResponseEntity.ok(reportVO);
    }
}

