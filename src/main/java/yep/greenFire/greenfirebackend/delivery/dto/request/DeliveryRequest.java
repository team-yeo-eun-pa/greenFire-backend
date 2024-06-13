package yep.greenFire.greenfirebackend.delivery.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import yep.greenFire.greenfirebackend.delivery.domain.type.DeliveryType;

@RequiredArgsConstructor
@Getter
public class DeliveryRequest {

    private Long storeOrderCode;

    // -- 판매자 배송 처리

    // 배송사, 운송장 번호
    private String deliveryCompany;
    private String transportNumber;

    // 일반 배송, 수거, 재배송
    private DeliveryType deliveryType;

}
