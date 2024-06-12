package yep.greenFire.greenfirebackend.store.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import yep.greenFire.greenfirebackend.auth.type.CustomUser;
import yep.greenFire.greenfirebackend.store.dto.request.StoreCloseRequest;
import yep.greenFire.greenfirebackend.store.dto.request.StoreProfileUpdateRequest;
import yep.greenFire.greenfirebackend.store.dto.response.StoreListResponse;
import yep.greenFire.greenfirebackend.store.dto.response.StoreProfileResponse;
import yep.greenFire.greenfirebackend.store.service.StoreService;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/") // 여기 변경 시 미리 말씀해 주세요!
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    // 판매자 보유 스토어 목록 조회
    @GetMapping("/seller/mystore")
    public ResponseEntity<List<StoreListResponse>> getStoreList(@AuthenticationPrincipal CustomUser customUser) {
        List<StoreListResponse> storeList = storeService.getStoreList(customUser.getMemberCode());
        return ResponseEntity.ok(storeList);
    }

    // 관리자 승인 후 새로운 스토어 등록
    @PutMapping("/seller/mystore/{sellerCode}/regist")
    public ResponseEntity<Void> modifyNewStore(
            @PathVariable final Long sellerCode,
            @RequestBody @Valid final StoreProfileUpdateRequest profileRequest,
            @AuthenticationPrincipal final CustomUser customUser
    ) {
        storeService.modifyNewStore(sellerCode, profileRequest, customUser.getMemberCode());
        return ResponseEntity.noContent().build();
    }

    // 스토어 프로필 조회
    @GetMapping("/seller/mystore/{sellerCode}")
    public ResponseEntity<StoreProfileResponse> getStoreProfile(@PathVariable Long sellerCode) {
        StoreProfileResponse storeProfile = storeService.getStoreProfile(sellerCode);
        return ResponseEntity.ok(storeProfile);
    }

    // 스토어 프로필 수정
    @PutMapping("/seller/mystore/{sellerCode}")
    public ResponseEntity<Void> modifyStore(
            @PathVariable final Long sellerCode,
            @RequestBody @Valid final StoreProfileUpdateRequest profileRequest,
            @AuthenticationPrincipal final CustomUser customUser
    ) {

        storeService.modifyProfile(sellerCode, profileRequest, customUser.getMemberCode());

        return ResponseEntity.noContent().build();
    }

    // 스토어 정지
    @PutMapping("/seller/mystore/{sellerCode}/pause")
    public ResponseEntity<Void> pauseStore(
            @PathVariable final Long sellerCode,
            @RequestBody @Valid final StoreCloseRequest closeRequest,
            @AuthenticationPrincipal final CustomUser customUser
    ) {

        storeService.pauseStore(sellerCode, closeRequest, customUser.getMemberCode());

        return ResponseEntity.noContent().build();
    }
}
