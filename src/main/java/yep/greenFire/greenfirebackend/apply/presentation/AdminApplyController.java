package yep.greenFire.greenfirebackend.apply.presentation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yep.greenFire.greenfirebackend.apply.dto.response.AdminApplyResponse;
import yep.greenFire.greenfirebackend.apply.service.ApplyService;
import yep.greenFire.greenfirebackend.common.paging.Pagination;
import yep.greenFire.greenfirebackend.common.paging.PagingButtonInfo;
import yep.greenFire.greenfirebackend.common.paging.PagingResponse;

import java.net.URI;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminApplyController {

    private final ApplyService applyService;

    @GetMapping("/applies")
    public ResponseEntity<PagingResponse> getAdminApplies(
            @RequestParam(defaultValue = "1") final Integer page
            ) {

        final Page<AdminApplyResponse> applies = applyService.getAdminApplies(page);
        final PagingButtonInfo pagingButtonInfo = Pagination.getPagingButtonInfo(applies);
        final PagingResponse pagingResponse = PagingResponse.of(applies.getContent(), pagingButtonInfo);

        return ResponseEntity.ok(pagingResponse);
    }

    @GetMapping("/applies/{sellerCode}")
    public ResponseEntity<AdminApplyResponse> getAdminApplyDetail(
            @PathVariable Long sellerCode
    ) {
        final AdminApplyResponse adminApplyResponse = applyService.getAdminApplyDetail(sellerCode);

        return ResponseEntity.ok(adminApplyResponse);
    }

//
//    @PostMapping("/applies/{sellerCode}")
//    public ResponseEntity<Void> save(
//            @RequestPart @Valid final ApplyCreateRequest applyCreateRequest,
//            @AuthenticationPrincipal final CustomUser customUser,
//            @RequestPart final MultipartFile businessImg
//            ) {
//
//        final Long applyCode = applyService.save(applyCreateRequest, businessImg, customUser.getMemberCode());
//
//        log.info(applyCode.toString());
//        return ResponseEntity.created(URI.create("/admin/applies/" + applyCode)).build();
//    }
//
//    @PutMapping("/applies/{sellerCode}")
//    public ResponseEntity<Void> modify(
//            @PathVariable final Long sellerCode,
//            @RequestPart @Valid final ApplyUpdateRequest applyRequest,
//            @RequestPart(required = false) final MultipartFile businessImg,
//            @AuthenticationPrincipal final CustomUser customUser
//    ) {
//
//        log.info("Received applyRequest: {}", applyRequest);
//        if (businessImg != null) {
//            log.info("Received businessImg: {}", businessImg.getOriginalFilename());
//        } else {
//            log.info("No businessImg provided");
//        }
//
//        applyService.modify(sellerCode, applyRequest, businessImg, customUser.getMemberCode());
//
//        return ResponseEntity.created(URI.create("/admin/applies/" + sellerCode)).build();
//    }
//
//    @PostMapping("/applies/{sellerCode}/cancel")
//    public ResponseEntity<Void> cancel(
//            @PathVariable final Long sellerCode,
//            @RequestPart @Valid final ApplyUpdateRequest applyRequest,
//            @AuthenticationPrincipal final CustomUser customUser
//    ) {
//
//        log.info("Cancel request for sellerCode: {}, with applyRequest: {}", sellerCode, applyRequest);
//
//        applyService.cancel(sellerCode, applyRequest, customUser.getMemberCode());
//
//        return ResponseEntity.noContent().build();
//    }

}
