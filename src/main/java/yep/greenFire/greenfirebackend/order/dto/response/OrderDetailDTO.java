package yep.greenFire.greenfirebackend.order.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class OrderDetailDTO {

    private final Long orderDetailCode;
    private final Long optionCode;
    private final Long optionPrice;
    private final Long orderQuantity;

    private final String optionName;
    private final String productName;
    private final String productImg;


}
