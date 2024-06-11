package yep.greenFire.greenfirebackend.product.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import yep.greenFire.greenfirebackend.product.domain.entity.Category;
import yep.greenFire.greenfirebackend.product.domain.entity.Product;
import yep.greenFire.greenfirebackend.product.domain.type.SellableStatus;
import java.util.Date;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class SellerProductsResponse {

    private final Long productCode;
    private final String productName;
    private final Long categoryCode;
    private final String categoryTitle;
    private final Long storeCode;
    private final Long price;
    private final Date registDate;
    private final SellableStatus sellableStatus;


    public static SellerProductsResponse from(final Product product, Category category) {
        return new SellerProductsResponse(
                product.getProductCode(),
                product.getProductName(),
                product.getCategoryCode(),
                category.getCategoryTitle(),
                product.getStoreCode(),
                product.getPrice(),
                product.getRegistDate(),
                product.getSellableStatus()
        );

    }
}
