package yep.greenFire.greenfirebackend.delivery.domain.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum DeliveryStatus {

    SHIPPED("배송 중"),
    DELIVERED("배송 완료");

    private final String deliveryStatus;

    DeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    @JsonValue
    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    @JsonCreator
    public static DeliveryStatus fromValue(String value) {
        for (DeliveryStatus deliveryStatus : DeliveryStatus.values()) {
            if (deliveryStatus.getDeliveryStatus().equals(value)) {
                return deliveryStatus;
            }
        }
        throw new IllegalArgumentException("Unknown deliveryStatus: " + value);
    }

}
