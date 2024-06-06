package yep.greenFire.greenfirebackend.report.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yep.greenFire.greenfirebackend.report.dto.request.ReportCreateRequest;
import yep.greenFire.greenfirebackend.report.service.ReportService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class ReportController {

    private final ReportService reportService;

    @PostMapping("/reports")
    public ResponseEntity<Void> createReport(@RequestPart ReportCreateRequest reportRequest) {
        System.out.println("report req :" + reportRequest);
        reportService.createMemberReport(reportRequest);
        return ResponseEntity.ok().build();
    }


}
