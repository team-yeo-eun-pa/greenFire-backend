package yep.greenFire.greenfirebackend.cart.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CartItemRequest {

    @Min(value = 1)
    private Long memberCode;
    @Min(value = 1)
    private Long optionCode;
    @Min(value = 1)
    private Long cartQuantity;
}
