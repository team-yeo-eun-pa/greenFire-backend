package yep.greenFire.greenfirebackend.admin.notice.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yep.greenFire.greenfirebackend.admin.notice.dto.response.AdminNoticeResponse;
import yep.greenFire.greenfirebackend.admin.notice.dto.response.AdminNoticesResponse;
import yep.greenFire.greenfirebackend.admin.notice.service.NoticeService;
import yep.greenFire.greenfirebackend.common.paging.Pagination;
import yep.greenFire.greenfirebackend.common.paging.PagingButtonInfo;
import yep.greenFire.greenfirebackend.common.paging.PagingResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("/adminNotices")
    public ResponseEntity<PagingResponse> getAdminNotices(
            @RequestParam(defaultValue = "1") final Integer page
    ){
        final Page<AdminNoticesResponse> notices = noticeService.getAdminNotices(page);
        final PagingButtonInfo pagingButtonInfo = Pagination.getPagingButtonInfo(notices);
        final PagingResponse pagingResponse = PagingResponse.of(notices.getContent(), pagingButtonInfo);

        return ResponseEntity.ok(pagingResponse);
    }

    @GetMapping("/notices/{noticeCode}")
    public ResponseEntity<AdminNoticeResponse> getAdminNotice (@PathVariable final int noticeCode) {

        final AdminNoticeResponse adminNoticeResponse = noticeService.getAdminNotice(noticeCode);

        return ResponseEntity.ok(adminNoticeResponse);
    }

}
