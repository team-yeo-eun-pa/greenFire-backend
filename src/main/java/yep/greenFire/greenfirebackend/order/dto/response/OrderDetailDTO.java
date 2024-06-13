package yep.greenFire.greenfirebackend.order.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class OrderDetailDTO {

    private  Long orderDetailCode;
    private  Long optionCode;
    private  Long optionPrice;
    private  Long orderQuantity;

    private String optionName;
    private String productName;
    private String productImg;

}
