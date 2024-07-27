package yep.greenFire.greenfirebackend.product.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import yep.greenFire.greenfirebackend.product.domain.type.ProductOptionAppearActivate;

@Getter
@RequiredArgsConstructor
public class ProductOptionCreateRequest {

    @NotBlank
    private final String optionName;
    @Min(value = 0)
    private final Long optionPrice;
    @Min(value = 0)
    private final Long optionStock;
}
