package yep.greenFire.greenfirebackend.delivery.domain.type;

import com.fasterxml.jackson.annotation.JsonValue;
import yep.greenFire.greenfirebackend.payment.domain.type.PaymentWay;

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

    public static DeliveryType fromValue(String value) {
        for (DeliveryType deliveryType : DeliveryType.values()) {
            if (deliveryType.getDeliveryType().equals(value)) {
                return deliveryType;
            }
        }
        throw new IllegalArgumentException("Unknown deliveryType: " + value);
    }

}
