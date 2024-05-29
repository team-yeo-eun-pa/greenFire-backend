package yep.greenFire.greenfirebackend.order.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yep.greenFire.greenfirebackend.auth.type.CustomUser;
import yep.greenFire.greenfirebackend.order.dto.request.OrderCreateRequest;
import yep.greenFire.greenfirebackend.order.service.OrderService;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    /* 주문 등록 */
    @PostMapping("/orders")
    public ResponseEntity<Void> save(
            @RequestBody @Valid final OrderCreateRequest orderRequest,
            @AuthenticationPrincipal final CustomUser customUser
    ){

        orderService.save(orderRequest, customUser.getMemberCode());

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
