package yep.greenFire.greenfirebackend.admin.notice.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import yep.greenFire.greenfirebackend.admin.notice.domain.entity.AdminMember;
import yep.greenFire.greenfirebackend.admin.notice.dto.request.NoticeCreateRequest;
import yep.greenFire.greenfirebackend.admin.notice.dto.request.NoticeUpdateRequest;
import yep.greenFire.greenfirebackend.admin.notice.dto.response.AdminNoticeResponse;
import yep.greenFire.greenfirebackend.admin.notice.dto.response.AdminNoticesResponse;
import yep.greenFire.greenfirebackend.admin.notice.service.NoticeService;
import yep.greenFire.greenfirebackend.common.paging.Pagination;
import yep.greenFire.greenfirebackend.common.paging.PagingButtonInfo;
import yep.greenFire.greenfirebackend.common.paging.PagingResponse;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("/adminNotices")
    public ResponseEntity<PagingResponse> getAdminNotices(
            @RequestParam(defaultValue = "1") final Integer page,
            @AuthenticationPrincipal final AdminMember adminMember
    ){
        final Page<AdminNoticesResponse> notices = noticeService.getAdminNotices(page);
        final PagingButtonInfo pagingButtonInfo = Pagination.getPagingButtonInfo(notices);
        final PagingResponse pagingResponse = PagingResponse.of(notices.getContent(), pagingButtonInfo);

        return ResponseEntity.ok(pagingResponse);
    }

    @GetMapping("/notices/{noticeCode}")
    public ResponseEntity<AdminNoticeResponse> getAdminNotice (@PathVariable final Long noticeCode,
                                                               @AuthenticationPrincipal final AdminMember adminMember) {

        final AdminNoticeResponse adminNoticeResponse = noticeService.getAdminNotice(noticeCode);

        return ResponseEntity.ok(adminNoticeResponse);
    }

    @PostMapping("/adminNotices")
    public ResponseEntity<Void> save(
            @RequestBody @Valid final NoticeCreateRequest noticeCreateRequest,
            @AuthenticationPrincipal final AdminMember adminMember
            ){
       noticeService.save(noticeCreateRequest, 1);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @PutMapping("/adminNotices/{noticeCode}")
    public ResponseEntity<Void> modify(
            @PathVariable final Long noticeCode,
            @RequestBody @Valid final NoticeUpdateRequest noticeUpdateRequest){

        System.out.println(noticeUpdateRequest);
        noticeService.modify(noticeCode, noticeUpdateRequest);

        return ResponseEntity.created(URI.create("/admin/adminNotices/" + noticeCode)).build();
    }

    @DeleteMapping("/adminNotices/{noticeCode}")
    public ResponseEntity<Void> remove(@PathVariable final Long noticeCode) {
        noticeService.remove(noticeCode);

        return ResponseEntity.noContent().build();
    }

}
