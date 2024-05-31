package yep.greenFire.greenfirebackend.product.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import yep.greenFire.greenfirebackend.product.domain.entity.Product;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductsResponse {

    private final Long productCode;
    private final String productName;
    private final String categoryName;
    private final String storeName;

    public static ProductsResponse from(final Product product) {
        return new ProductsResponse (
            product.getProductCode(),
            product.getProductName(),
            product.getCategory().getCategoryTitle(),
            product.getStore().getStoreName()

        );
    }

}
