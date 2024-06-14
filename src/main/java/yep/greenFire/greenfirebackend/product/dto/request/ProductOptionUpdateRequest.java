package yep.greenFire.greenfirebackend.product.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProductOptionUpdateRequest {

        //    @Min(value = 1)
//    private final Long productCode;
        @NotBlank
        private final String optionName;
        //    @NotNull
//    private final ProductOptionAppearActivate optionAppearActivate;
        @Min(value = 0)
        private final Long optionPrice;
        @Min(value = 0)
        private final Long optionStock;

}
