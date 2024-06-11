package yep.greenFire.greenfirebackend.apply.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import yep.greenFire.greenfirebackend.apply.dto.request.ApplyCreateRequest;
import yep.greenFire.greenfirebackend.apply.dto.request.ApplyUpdateRequest;
import yep.greenFire.greenfirebackend.apply.dto.response.AdminApplyResponse;
import yep.greenFire.greenfirebackend.apply.dto.response.ApplyResponse;
import yep.greenFire.greenfirebackend.apply.service.ApplyService;
import yep.greenFire.greenfirebackend.auth.type.CustomUser;
import yep.greenFire.greenfirebackend.common.paging.Pagination;
import yep.greenFire.greenfirebackend.common.paging.PagingButtonInfo;
import yep.greenFire.greenfirebackend.common.paging.PagingResponse;

import java.net.URI;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/members/mypage/apply")
public class ApplyController {

    private final ApplyService applyService;

    @GetMapping("/{memberId}/applies")
//    @PreAuthorize("#memberId == authentication.principal.username")
    public ResponseEntity<PagingResponse> getApplies(
            @RequestParam(defaultValue = "1") final Integer page,
            @PathVariable String memberId,
            @AuthenticationPrincipal CustomUser customUser) {

        final Page<ApplyResponse> applies = applyService.getApplies(page, customUser.getMemberCode());
        final PagingButtonInfo pagingButtonInfo = Pagination.getPagingButtonInfo(applies);
        final PagingResponse pagingResponse = PagingResponse.of(applies.getContent(), pagingButtonInfo);

        return ResponseEntity.ok(pagingResponse);
    }

    @GetMapping("/{memberId}/{sellerCode}")
    public ResponseEntity<ApplyResponse> getApplyDetail(
            @PathVariable Long sellerCode,
            @PathVariable String memberId,
            @AuthenticationPrincipal CustomUser customUser
    ) {
        final ApplyResponse applyResponse = applyService.getApplyDetail(customUser.getMemberCode(), sellerCode);

        return ResponseEntity.ok(applyResponse);
    }

    @PostMapping("/regist")
    public ResponseEntity<Void> save(
            @RequestPart @Valid final ApplyCreateRequest applyCreateRequest,
            @AuthenticationPrincipal final CustomUser customUser,
            @RequestPart final MultipartFile businessImg
            ) {

        final Long applyCode = applyService.save(applyCreateRequest, businessImg, customUser.getMemberCode());

        log.info(applyCode.toString());
        return ResponseEntity.created(URI.create("/admin/applies/" + applyCode)).build();
    }

    @PutMapping("/modify/{sellerCode}")
    public ResponseEntity<Void> modify(
            @PathVariable final Long sellerCode,
            @RequestPart @Valid final ApplyUpdateRequest applyRequest,
            @RequestPart(required = false) final MultipartFile businessImg,
            @AuthenticationPrincipal final CustomUser customUser
    ) {

        log.info("Received applyRequest: {}", applyRequest);
        if (businessImg != null) {
            log.info("Received businessImg: {}", businessImg.getOriginalFilename());
        } else {
            log.info("No businessImg provided");
        }

        applyService.modify(sellerCode, applyRequest, businessImg, customUser.getMemberCode());

        return ResponseEntity.created(URI.create("/admin/applies/" + sellerCode)).build();
    }

    @PostMapping("/modify/{sellerCode}/cancel")
    public ResponseEntity<Void> cancel(
            @PathVariable final Long sellerCode,
            @RequestPart @Valid final ApplyUpdateRequest applyRequest,
            @AuthenticationPrincipal final CustomUser customUser
    ) {

        log.info("Cancel request for sellerCode: {}, with applyRequest: {}", sellerCode, applyRequest);

        applyService.cancel(sellerCode, applyRequest, customUser.getMemberCode());

        return ResponseEntity.noContent().build();
    }

}
