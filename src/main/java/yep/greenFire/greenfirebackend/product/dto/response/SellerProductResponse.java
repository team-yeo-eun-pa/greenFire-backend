package yep.greenFire.greenfirebackend.product.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import yep.greenFire.greenfirebackend.product.domain.entity.Category;
import yep.greenFire.greenfirebackend.product.domain.entity.Product;
import yep.greenFire.greenfirebackend.product.domain.type.SellableStatus;
import yep.greenFire.greenfirebackend.store.domain.entity.Store;

import java.util.Date;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class SellerProductResponse {

    private final Long productCode;
    private final String productName;
    private final Long categoryCode;
    private final String categoryTitle;
    private final Long storeCode;
    private final String storeName;
    private final Long price;
    private final Date registDate;
    private final SellableStatus sellableStatus;

    public SellerProductResponse(Product product, Category category, Store store) {
        this.productCode = product.getProductCode();
        this.productName = product.getProductName();
        this.categoryCode = product.getCategoryCode();
        this.categoryTitle = category.getCategoryTitle();
        this.storeCode = product.getStoreCode();
        this.storeName = store.getStoreName();
        this.price = product.getPrice();
        this.registDate = product.getRegistDate();
        this.sellableStatus = product.getSellableStatus();
    }

}
