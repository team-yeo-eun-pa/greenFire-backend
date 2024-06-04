package yep.greenFire.greenfirebackend.report.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import yep.greenFire.greenfirebackend.report.dto.response.ReportsVO;
import yep.greenFire.greenfirebackend.report.service.ReportMembersService;
import yep.greenFire.greenfirebackend.common.paging.Pagination;
import yep.greenFire.greenfirebackend.common.paging.PagingButtonInfo;
import yep.greenFire.greenfirebackend.common.paging.PagingResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class ReportMembersController {

    private final ReportMembersService reportMembersService;

    @GetMapping("/suspend/members")
    public ResponseEntity<PagingResponse> getReportedMember(
            @RequestParam(defaultValue = "1") final Integer page
    ) {
        final Page<ReportsVO> reports = reportMembersService.getMembersByStatus(page);
        final PagingButtonInfo pagingButtonInfo = Pagination.getPagingButtonInfo(reports);
        final PagingResponse pagingResponse = PagingResponse.of(reports.getContent(), pagingButtonInfo);

        return ResponseEntity.ok(pagingResponse);
    }
}
