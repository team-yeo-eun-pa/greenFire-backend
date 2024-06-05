package yep.greenFire.greenfirebackend.product.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import yep.greenFire.greenfirebackend.product.domain.entity.Product;
import yep.greenFire.greenfirebackend.product.domain.entity.ProductOption;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductOptionResponse {

    private final Long optionCode;
    private final Long productCode;
    private final String optionName;
    private final Long optionPrice;
    private final Long optionStock;

    public static ProductOptionResponse from(final ProductOption option) {
        return new ProductOptionResponse(
                option.getOptionCode(),
                option.getProductCode(),
                option.getOptionName(),
                option.getOptionPrice(),
                option.getOptionStock()
        );
    }
}
