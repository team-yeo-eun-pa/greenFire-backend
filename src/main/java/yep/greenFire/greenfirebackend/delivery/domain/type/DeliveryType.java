package yep.greenFire.greenfirebackend.delivery.domain.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum DeliveryType {
    SEND("일반 배송"),
    RETURN("수거"),
    RESEND("재배송");

    private final String deliveryType;

    DeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    @JsonValue
    public String getDeliveryType() {
        return deliveryType;
    }

    @JsonCreator
    public static DeliveryType fromValue(String value) {
        for (DeliveryType deliveryType : DeliveryType.values()) {
            if (deliveryType.getDeliveryType().equals(value)) {
                return deliveryType;
            }
        }
        throw new IllegalArgumentException("Unknown deliveryType: " + value);
    }

}
