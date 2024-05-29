package yep.greenFire.greenfirebackend.order.domain.type;

public enum OrderStatus {

    RECEIVED("received"),           // 주문 접수
    CONFIRMED("confirmed"),         // 주문 확인
    PROCESSING("processing"),       // 상품 준비
    READY_FOR_SHIPMENT("ready_for_shipment"), // 배송 준비
    SHIPPED("shipped"),             // 배송 중
    DELIVERED("delivered"),         // 배송 완료
    RETURN_REQUESTED("return_requested"), // 반품 요청
    REFUNDED("refunded"),           // 환불 완료
    COMPLETED("completed");         // 주문 완료

    private final String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return this.status;
    }
}
