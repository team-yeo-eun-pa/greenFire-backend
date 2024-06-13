package yep.greenFire.greenfirebackend.product.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import yep.greenFire.greenfirebackend.product.domain.entity.Category;
import yep.greenFire.greenfirebackend.product.domain.entity.Product;
import yep.greenFire.greenfirebackend.product.domain.type.SellableStatus;
import yep.greenFire.greenfirebackend.store.domain.entity.Store;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductsResponse {

    private final Long productCode;
    private final String productName;
    private final Long categoryCode;
    private final String categoryTitle;
    private final Long storeCode;
    private final String storeName;
    private final Long price;
    private final LocalDateTime registDate;
    private final SellableStatus sellableStatus;
    private final String productImage;

//    public static ProductsResponse from(final Product product, Category category, Store store) {
//        return new ProductsResponse(
//                product.getProductCode(),
//                product.getProductName(),
//                product.getCategoryCode(),
//                category.getCategoryTitle(),
//                product.getStoreCode(),
//                store.getStoreName(),
//                product.getPrice(),
//                product.getRegistDate(),
//                product.getSellableStatus()
//        );
//    }

    public ProductsResponse(Product product, Category category, Store store) {
        this.productCode = product.getProductCode();
        this.productName = product.getProductName();
        this.categoryCode = product.getCategoryCode();
        this.categoryTitle = category.getCategoryTitle();
        this.storeCode = product.getStoreCode();
        this.storeName = store.getStoreName();
        this.price = product.getPrice();
        this.registDate = product.getRegistDate();
        this.sellableStatus = product.getSellableStatus();
        this.productImage = product.getProductImage();
    }



}