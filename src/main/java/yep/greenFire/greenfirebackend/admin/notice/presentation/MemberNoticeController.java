package yep.greenFire.greenfirebackend.admin.notice.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yep.greenFire.greenfirebackend.admin.notice.dto.response.MemberNoticeResponse;
import yep.greenFire.greenfirebackend.admin.notice.dto.response.MemberNoticesResponse;
import yep.greenFire.greenfirebackend.admin.notice.service.NoticeService;
import yep.greenFire.greenfirebackend.common.paging.Pagination;
import yep.greenFire.greenfirebackend.common.paging.PagingButtonInfo;
import yep.greenFire.greenfirebackend.common.paging.PagingResponse;


@RestController
@RequiredArgsConstructor
@RequestMapping("/Member")
public class MemberNoticeController {

    private final NoticeService noticeService;


    @GetMapping("/notices")
    public ResponseEntity<PagingResponse> getMemberNotices(
            @RequestParam(defaultValue = "1") final Integer page,
            @RequestParam(required = false) final Integer noticeCode
    ) {

        final Page<MemberNoticesResponse> notices = noticeService.getMemberNotices(page, noticeCode);
        final PagingButtonInfo pagingButtonInfo = Pagination.getPagingButtonInfo(notices);
        final PagingResponse pagingResponse = PagingResponse.of(notices.getContent(), pagingButtonInfo);

        return ResponseEntity.ok(pagingResponse);

    }

    @GetMapping("/notices/{noticeCode}")
    public ResponseEntity<MemberNoticeResponse> getMemberNotice(@PathVariable final Long noticeCode){
        final MemberNoticeResponse memberNoticesResponse = noticeService.getMemberNotice(noticeCode);

        return ResponseEntity.ok(memberNoticesResponse);
    }


}
