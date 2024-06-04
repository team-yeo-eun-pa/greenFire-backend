package yep.greenFire.greenfirebackend.apply.presentation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import yep.greenFire.greenfirebackend.apply.dto.response.ApplyResponse;
import yep.greenFire.greenfirebackend.apply.service.ApplyService;
import yep.greenFire.greenfirebackend.auth.type.CustomUser;
import yep.greenFire.greenfirebackend.common.paging.Pagination;
import yep.greenFire.greenfirebackend.common.paging.PagingButtonInfo;
import yep.greenFire.greenfirebackend.common.paging.PagingResponse;

@Slf4j
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
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

}
