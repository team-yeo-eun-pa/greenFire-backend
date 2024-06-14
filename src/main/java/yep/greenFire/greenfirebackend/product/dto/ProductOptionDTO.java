package yep.greenFire.greenfirebackend.product.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import yep.greenFire.greenfirebackend.product.domain.entity.ProductOption;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductOptionDTO {
    private Long optionCode;
    private Long productCode;
    private String optionName;
    private Long optionPrice;
    private Long optionStock;

    public ProductOptionDTO(Long optionCode, Long productCode, String optionName, Long optionPrice, Long optionStock) {
        this.optionCode = optionCode;
        this.productCode = productCode;
        this.optionName = optionName;
        this.optionPrice = optionPrice;
        this.optionStock = optionStock;
    }

    public static ProductOptionDTO from(final ProductOption option) {
        return new ProductOptionDTO(
                option.getOptionCode(),
                option.getProductCode(),
                option.getOptionName(),
                option.getOptionPrice(),
                option.getOptionStock()
        );
    }


}
