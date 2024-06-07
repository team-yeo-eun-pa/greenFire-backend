package yep.greenFire.greenfirebackend.order.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderDetailDTO {

    private final Long orderDetailCode;
    private final Long optionCode;
    private final Long optionPrice;
    private final Long orderQuantity;

}
