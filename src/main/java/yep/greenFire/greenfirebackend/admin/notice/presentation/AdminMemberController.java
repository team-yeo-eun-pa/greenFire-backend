package yep.greenFire.greenfirebackend.admin.notice.presentation;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import yep.greenFire.greenfirebackend.admin.notice.dto.response.AdminMemberResponse;
import yep.greenFire.greenfirebackend.admin.notice.service.AdminMemberService;
import yep.greenFire.greenfirebackend.common.paging.Pagination;
import yep.greenFire.greenfirebackend.common.paging.PagingButtonInfo;
import yep.greenFire.greenfirebackend.common.paging.PagingResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminMemberController {

    private final AdminMemberService adminMemberService;

    @GetMapping("/members")
    public ResponseEntity<PagingResponse> getAdminNotice(
            @RequestParam(defaultValue = "1") final Integer page
    ) {
        final Page<AdminMemberResponse> members = adminMemberService.getAdminMembers(page);
        final PagingButtonInfo pagingButtonInfo = Pagination.getPagingButtonInfo(members);
        final PagingResponse pagingResponse = PagingResponse.of(members.getContent(), pagingButtonInfo);

        return ResponseEntity.ok(pagingResponse);
    }
}
