package yep.greenFire.greenfirebackend.order.domain.type;

public enum OrderStatus {

    RECEIVED("received"),           // 주문 접수
    REJECTED("rejected"),           // 주문 거절
    PROCESSING("processing"),       // 상품 준비
    SHIPPED("shipped"),             // 배송 중
    DELIVERED("delivered"),         // 배송 완료
    RETURN_REQUESTED("return_requested"), // 반품 요청
    REFUNDED("refunded"),           // 환불 완료
    COMPLETED("completed");         // 주문 완료

    private final String orderStatus;

    OrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    @Override
    public String toString() {
        return this.orderStatus;
    }
}
