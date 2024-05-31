package yep.greenFire.greenfirebackend.product.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import yep.greenFire.greenfirebackend.product.domain.entity.Product;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductResponse {

    private final Long productCode;
    private final String productName;
    private final String categoryName;
    private final String storeName;
    private final String optionName;

    public static ProductResponse from(final Product product) {
        return new ProductResponse(
                product.getProductCode(),
                product.getProductName(),
                product.getCategory().getCategoryTitle(),
                product.getStore().getStoreName(),
                product.
        );
    }
}
