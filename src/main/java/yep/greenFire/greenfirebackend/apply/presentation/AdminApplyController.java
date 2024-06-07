package yep.greenFire.greenfirebackend.apply.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yep.greenFire.greenfirebackend.apply.dto.request.ApplyUpdateRequest;
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

    @PutMapping("/applies/{sellerCode}/accept")
    public ResponseEntity<Void> applyAccept(
            @PathVariable final Long sellerCode,
            @RequestBody @Valid final ApplyUpdateRequest applyRequest
    ) {
        applyService.applyAccept(sellerCode, applyRequest);
        return ResponseEntity.created(URI.create("/admin/applies/" + sellerCode)).build();
    }

    @PostMapping("/applies/{sellerCode}/reject")
    public ResponseEntity<Void> applyReject(
            @PathVariable final Long sellerCode,
            @RequestBody @Valid final ApplyUpdateRequest applyRequest
    ) {
        applyService.applyReject(sellerCode, applyRequest);
        return ResponseEntity.created(URI.create("/admin/applies/" + sellerCode)).build();
    }

}
