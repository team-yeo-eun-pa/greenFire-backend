package yep.greenFire.greenfirebackend.order.domain.type;

import com.fasterxml.jackson.annotation.JsonValue;

public enum OrderStatus {

    RECEIVED("주문 접수"),           // 주문 접수
    REJECTED("주문 거절"),           // 주문 거절
    PROCESSING("상품 준비"),       // 상품 준비
    SHIPPED("배송 중"),             // 배송 중
    DELIVERED("배송 완료"),         // 배송 완료
    RETURN_REQUESTED("반품 요청"), // 반품 요청
    REFUNDED("반품 완료"),           // 환불 완료
    COMPLETED("주문 확정");         // 주문 완료

    private final String orderStatus;

    OrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @JsonValue
    public String getOrderStatus() {
        return orderStatus;
    }

    @Override
    public String toString() {
        return this.orderStatus;
    }
}
