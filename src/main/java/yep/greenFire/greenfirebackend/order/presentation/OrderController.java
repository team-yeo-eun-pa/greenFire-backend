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

import java.util.Collections;

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

    // 회원 - 주문 조회
    @GetMapping("/members/orders")
//    @PreAuthorize("#memberId == authentication.principal.memberCode.toString()")
    public ResponseEntity<PagingResponse> getOrderByMemberCode(
            @RequestParam(defaultValue = "1") final Integer page,
            @AuthenticationPrincipal CustomUser customUser
    ){

        final Page<OrderResponse> orders = orderService.getOrderByMemberCode(customUser.getMemberCode(), page);

        // 주문이 없는 경우 빈 페이지 객체를 반환
        if (orders.isEmpty()) {
            // PagingButtonInfo의 생성자가 필요로 하는 인자를 전달
            PagingButtonInfo emptyPagingButtonInfo = new PagingButtonInfo(0, 0, 1, 1);
            return ResponseEntity.ok(new PagingResponse(Collections.emptyList(), emptyPagingButtonInfo));
        }

        final PagingButtonInfo pagingButtonInfo = Pagination.getPagingButtonInfo(orders);
        final PagingResponse pagingResponse = PagingResponse.of(orders.getContent(), pagingButtonInfo);
        return ResponseEntity.ok(pagingResponse);
    }

    // 스토어 - 주문 조회
    @GetMapping("/store/{storeCode}/orders")
// @PreAuthorize("#memberId == authentication.principal.memberCode.toString()")
    public ResponseEntity<PagingResponse> getStoreOrders(
            @RequestParam(defaultValue = "1") final Integer page,
            @PathVariable Long storeCode
    ) {

        final Page<OrderResponse> storeOrders = orderService.getOrderByStoreCode(storeCode, page);

        // 주문이 없는 경우 빈 페이지 객체를 반환
        if (storeOrders.isEmpty()) {
            // PagingButtonInfo의 생성자가 필요로 하는 인자를 전달
            PagingButtonInfo emptyPagingButtonInfo = new PagingButtonInfo(0, 0, 1, 1);
            return ResponseEntity.ok(new PagingResponse(Collections.emptyList(), emptyPagingButtonInfo));
        }

        final PagingButtonInfo pagingButtonInfo = Pagination.getPagingButtonInfo(storeOrders);
        final PagingResponse pagingResponse = PagingResponse.of(storeOrders.getContent(), pagingButtonInfo);
        return ResponseEntity.ok(pagingResponse);
    }

//    // 스토어 & 주문 상태에 따른 - 주문 조회
//    @GetMapping("/store/{storeCode}/orders/{orderStatus}")
//// @PreAuthorize("#memberId == authentication.principal.memberCode.toString()")
//    public ResponseEntity<PagingResponse> getStoreOrderStatus(
//            @RequestParam(defaultValue = "1") final Integer page,
////            @PathVariable String storeCode,
//            @RequestParam Long storeCode,
//            @RequestParam List<String> orderStatus
//    ) {
//        final Page<OrderResponse> storeOrders = orderService.getStoreOrderStatus(storeCode, orderStatus, page);
//
//        // 주문이 없는 경우 빈 페이지 객체를 반환
//        if (storeOrders.isEmpty()) {
//            // PagingButtonInfo의 생성자가 필요로 하는 인자를 전달
//            PagingButtonInfo emptyPagingButtonInfo = new PagingButtonInfo(0, 0, 1, 1);
//            return ResponseEntity.ok(new PagingResponse(Collections.emptyList(), emptyPagingButtonInfo));
//        }
//
//        final PagingButtonInfo pagingButtonInfo = Pagination.getPagingButtonInfo(storeOrders);
//        final PagingResponse pagingResponse = PagingResponse.of(storeOrders.getContent(), pagingButtonInfo);
//        return ResponseEntity.ok(pagingResponse);
//    }
//
//    // 스토어 - 주문 상태 변경
//    @PostMapping("/orders/apply")
//    public ResponseEntity<OrderApprovalRequest> modifyOrderStatus(@RequestBody OrderApprovalRequest orderRequest) {
//
////        orderService.modifyOrderStatus(orderRequest);
//
//        return ResponseEntity.ok(orderRequest);
//    }
}
