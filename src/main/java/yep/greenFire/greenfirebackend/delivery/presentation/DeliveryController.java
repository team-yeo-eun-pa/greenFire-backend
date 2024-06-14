package yep.greenFire.greenfirebackend.delivery.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yep.greenFire.greenfirebackend.delivery.dto.request.DeliveryRequest;
import yep.greenFire.greenfirebackend.delivery.service.DeliveryService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class DeliveryController {

    private final DeliveryService deliveryService;

    // 배송 정보 등록
    @PostMapping("/delivery")
    public ResponseEntity<Void> save(
            @RequestBody @Valid final DeliveryRequest deliveryRequest
    ){

        deliveryService.save(deliveryRequest);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
