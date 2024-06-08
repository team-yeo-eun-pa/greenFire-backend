package yep.greenFire.greenfirebackend.order.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import yep.greenFire.greenfirebackend.auth.type.CustomUser;
import yep.greenFire.greenfirebackend.common.paging.Pagination;
import yep.greenFire.greenfirebackend.common.paging.PagingButtonInfo;
import yep.greenFire.greenfirebackend.common.paging.PagingResponse;
import yep.greenFire.greenfirebackend.order.dto.request.OrderCreateRequest;
import yep.greenFire.greenfirebackend.order.dto.response.OrderResponse;
import yep.greenFire.greenfirebackend.order.service.OrderService;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // 주문 등록
    @PostMapping("/orders")
    public ResponseEntity<Void> save(
            @RequestBody @Valid final OrderCreateRequest orderRequest,
            @AuthenticationPrincipal final CustomUser customUser
    ){

        orderService.save(orderRequest, customUser.getMemberCode());

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // 회원 - 주문 목록 조회
    @GetMapping("/members/{memberId}/orders")
//    @PreAuthorize("#memberId == authentication.principal.memberCode.toString()")
    public ResponseEntity<PagingResponse> getOrders(
            @RequestParam(defaultValue = "1") final Integer page,
            @PathVariable String memberId,
            @AuthenticationPrincipal CustomUser customUser
    ){
        final Page<OrderResponse> orders = orderService.getOrders(customUser.getMemberCode(), page);
        final PagingButtonInfo pagingButtonInfo = Pagination.getPagingButtonInfo(orders);
        final PagingResponse pagingResponse = PagingResponse.of(orders.getContent(), pagingButtonInfo);
        return ResponseEntity.ok(pagingResponse);
    }

    // 회원 - 주문 상세 조회
    @GetMapping("/members/{memberId}/orders/{orderCode}")
    public ResponseEntity<OrderResponse> getOrderDetail(
            @PathVariable String memberId,
            @PathVariable Long orderCode,
            @AuthenticationPrincipal CustomUser customUser
    ) {

        OrderResponse orderResponse = orderService.getOrderDetail(customUser.getMemberCode(), orderCode);

        return ResponseEntity.ok(orderResponse);
    }
}
