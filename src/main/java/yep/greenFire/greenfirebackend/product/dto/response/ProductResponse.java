package yep.greenFire.greenfirebackend.product.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import yep.greenFire.greenfirebackend.product.domain.entity.Category;
import yep.greenFire.greenfirebackend.product.domain.entity.Product;
import yep.greenFire.greenfirebackend.product.domain.type.SellableStatus;
import yep.greenFire.greenfirebackend.store.domain.entity.Store;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductResponse {

    private final Long productCode;
    private final String productName;
    private final Long categoryCode;
    private final String categoryTitle;
    private final Long storeCode;
    private final String storeName;
    private final SellableStatus sellableStatus;



    public static ProductResponse from(final Product product, Category category, Store store) {
        return new ProductResponse(
                product.getProductCode(),
                product.getProductName(),
                product.getCategoryCode(),
                category.getCategoryTitle(),
                product.getStoreCode(),
                store.getStoreName(),
                product.getSellableStatus()
        );
    }

    public ProductResponse(final Product product, Category category, Store store) {
        this.productCode = product.getProductCode();
        this.productName = product.getProductName();
        this.categoryCode = product.getCategoryCode();
        this.categoryTitle = category.getCategoryTitle();
        this.storeCode = product.getStoreCode();
        this.storeName = store.getStoreName();
        this.sellableStatus = product.getSellableStatus();
    }
}
