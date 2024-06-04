package yep.greenFire.greenfirebackend.user.seller.presentation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import yep.greenFire.greenfirebackend.auth.type.CustomUser;
import yep.greenFire.greenfirebackend.user.seller.dto.response.StoreListResponse;
import yep.greenFire.greenfirebackend.user.seller.dto.response.StoreProfileResponse;
import yep.greenFire.greenfirebackend.user.seller.service.SellerService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/seller")
@RequiredArgsConstructor
public class SellerController {

    private final SellerService sellerService;

    // 판매자 보유 스토어 목록 조회
    @GetMapping("/mystore")
    public ResponseEntity<List<StoreListResponse>> getStoreList(@AuthenticationPrincipal CustomUser customUser) {
        List<StoreListResponse> storeList = sellerService.getStoreList(customUser.getMemberCode());
        return ResponseEntity.ok(storeList);
    }

    // 스토어 프로필 조회
    @GetMapping("/mystore/{storeCode}")
    public ResponseEntity<StoreProfileResponse> getStoreProfile(@PathVariable Long storeCode) {
        StoreProfileResponse storeProfile = sellerService.getStoreProfile(storeCode);
        return ResponseEntity.ok(storeProfile);
    }


}