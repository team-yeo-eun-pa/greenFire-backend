package yep.greenFire.greenfirebackend.admin.report.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import yep.greenFire.greenfirebackend.user.member.dto.response.MemberResponse;
import yep.greenFire.greenfirebackend.admin.report.service.ReportMemberService;
import yep.greenFire.greenfirebackend.common.paging.Pagination;
import yep.greenFire.greenfirebackend.common.paging.PagingButtonInfo;
import yep.greenFire.greenfirebackend.common.paging.PagingResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class ReportMemberController {


    private final ReportMemberService reportMemberService;

    @GetMapping("/suspend/members")
    public ResponseEntity<PagingResponse> getReportedMember(
            @RequestParam(defaultValue = "1") final Integer page
    ) {
        final Page<MemberResponse> members = reportMemberService.getReportedMember(page);
        final PagingButtonInfo pagingButtonInfo = Pagination.getPagingButtonInfo(members);
        final PagingResponse pagingResponse = PagingResponse.of(members.getContent(), pagingButtonInfo);

        return ResponseEntity.ok(pagingResponse);
    }
}
