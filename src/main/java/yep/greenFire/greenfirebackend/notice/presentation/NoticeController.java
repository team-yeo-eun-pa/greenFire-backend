package yep.greenFire.greenfirebackend.notice.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import yep.greenFire.greenfirebackend.auth.type.CustomUser;
import yep.greenFire.greenfirebackend.notice.dto.request.NoticeCreateRequest;
import yep.greenFire.greenfirebackend.notice.dto.request.NoticeUpdateRequest;
import yep.greenFire.greenfirebackend.notice.dto.response.AdminNoticeResponse;
import yep.greenFire.greenfirebackend.notice.dto.response.AdminNoticesResponse;
import yep.greenFire.greenfirebackend.notice.service.NoticeService;
import yep.greenFire.greenfirebackend.common.paging.Pagination;
import yep.greenFire.greenfirebackend.common.paging.PagingButtonInfo;
import yep.greenFire.greenfirebackend.common.paging.PagingResponse;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("/notices")
    public ResponseEntity<PagingResponse> getAdminNotices(
            @RequestParam(defaultValue = "1") final Integer page
    ){

        final Page<AdminNoticesResponse> notices = noticeService.getAdminNotices(page);
        final PagingButtonInfo pagingButtonInfo = Pagination.getPagingButtonInfo(notices);
        final PagingResponse pagingResponse = PagingResponse.of(notices.getContent(), pagingButtonInfo);

        return ResponseEntity.ok(pagingResponse);
    }

    @GetMapping("/notices/{noticeCode}")
    public ResponseEntity<AdminNoticeResponse> getAdminNotice (@PathVariable final Long noticeCode
                                                               ) {
        System.out.println("noticeCode = " + noticeCode);
        final AdminNoticeResponse adminNoticeResponse = noticeService.getAdminNotice(noticeCode);

        return ResponseEntity.ok(adminNoticeResponse);
    }

    @PostMapping("/notice-create")
    public ResponseEntity<Void> save(
            @RequestPart(value="file",required = false)  @Valid final NoticeCreateRequest noticeCreateRequest,
            @AuthenticationPrincipal final CustomUser customUser
            ){
       noticeService.save(noticeCreateRequest, customUser.getMemberCode());

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @PutMapping("/notices/{noticeCode}")
    public ResponseEntity<Void> modify(
            @PathVariable final Long noticeCode,
            @RequestBody @Valid final NoticeUpdateRequest noticeUpdateRequest){

        System.out.println(noticeUpdateRequest);
        noticeService.modify(noticeCode, noticeUpdateRequest);

        return ResponseEntity.created(URI.create("/admin/adminNotices/" + noticeCode)).build();
    }

    @DeleteMapping("/notices/{noticeCode}")
    public ResponseEntity<Void> remove(@PathVariable final Long noticeCode) {
        noticeService.remove(noticeCode);

        return ResponseEntity.noContent().build();
    }

}
