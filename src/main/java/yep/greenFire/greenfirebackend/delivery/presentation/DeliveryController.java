package yep.greenFire.greenfirebackend.delivery.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import yep.greenFire.greenfirebackend.auth.type.CustomUser;
import yep.greenFire.greenfirebackend.delivery.dto.request.DeliveryAddressRequest;
import yep.greenFire.greenfirebackend.delivery.dto.response.DeliveryAddressResponse;
import yep.greenFire.greenfirebackend.delivery.service.DeliveryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    // 배송지 등록
    @PostMapping("/delivery-addresses")
    public ResponseEntity<Void> save(
            @RequestBody @Valid final DeliveryAddressRequest deliveryAddressRequest,
            @AuthenticationPrincipal final CustomUser customUser
    ){

        deliveryService.save(deliveryAddressRequest, customUser.getMemberCode());

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // 배송지 조회
    @GetMapping("/members/{memberId}/delivery-addresses")
    public ResponseEntity<List<DeliveryAddressResponse>> getDeliveryAddress(
            @PathVariable String memberId,
            @AuthenticationPrincipal CustomUser customUser
    ) {
        List<DeliveryAddressResponse> deliveryAddresses = deliveryService.getDeliveryAddress(customUser.getMemberCode());
        return ResponseEntity.ok(deliveryAddresses);
    }

    // 배송지 삭제
    @DeleteMapping("/delivery-addresses/{deliveryAddressCode}")
    public ResponseEntity<Void> deleteDeliveryAddress(
            @PathVariable Long deliveryAddressCode,
            @AuthenticationPrincipal CustomUser customUser
    ) {
        deliveryService.deleteDeliveryAddress(deliveryAddressCode, customUser.getMemberCode());
        return ResponseEntity.noContent().build();
    }


}
