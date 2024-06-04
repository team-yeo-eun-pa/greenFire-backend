package yep.greenFire.greenfirebackend.notice.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yep.greenFire.greenfirebackend.notice.dto.response.MemberNoticeResponse;
import yep.greenFire.greenfirebackend.notice.dto.response.MemberNoticesResponse;
import yep.greenFire.greenfirebackend.common.paging.Pagination;
import yep.greenFire.greenfirebackend.common.paging.PagingButtonInfo;
import yep.greenFire.greenfirebackend.common.paging.PagingResponse;
import yep.greenFire.greenfirebackend.notice.service.MemberNoticeService;


@RestController
@RequiredArgsConstructor
@RequestMapping("/Member")
public class MemberNoticeController {

    private final MemberNoticeService memberNoticeService;


    @GetMapping("/notices")
    public ResponseEntity<PagingResponse> getMemberNotices(
            @RequestParam(defaultValue = "1") final Integer page
    ) {

        final Page<MemberNoticesResponse> notices = memberNoticeService.getMemberNotices(page);
        final PagingButtonInfo pagingButtonInfo = Pagination.getPagingButtonInfo(notices);
        final PagingResponse pagingResponse = PagingResponse.of(notices.getContent(), pagingButtonInfo);

        return ResponseEntity.ok(pagingResponse);

    }

    @GetMapping("/notices/{noticeCode}")
    public ResponseEntity<MemberNoticeResponse> getMemberNotice(@PathVariable final Long noticeCode){
        final MemberNoticeResponse memberNoticesResponse = memberNoticeService.getMemberNotice(noticeCode);

        return ResponseEntity.ok(memberNoticesResponse);
    }


}
