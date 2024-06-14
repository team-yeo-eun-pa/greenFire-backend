package yep.greenFire.greenfirebackend.product.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import yep.greenFire.greenfirebackend.product.domain.entity.Category;
import yep.greenFire.greenfirebackend.product.domain.entity.Product;
import yep.greenFire.greenfirebackend.product.domain.entity.ProductOption;
import yep.greenFire.greenfirebackend.product.domain.type.SellableStatus;
import yep.greenFire.greenfirebackend.store.domain.entity.Store;

@Getter
@Setter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductDTO {
    private final Long productCode;
    private final String productName;
    private final Long categoryCode;
    private final String categoryTitle;
    private final Long storeCode;
    private final String storeName;
    private final String productDescription;
    private final SellableStatus sellableStatus;
    private final String productImage;

    public ProductDTO(Product product, Category category, Store store){
        this.productCode = product.getProductCode();
        this.productName = product.getProductName();
        this.categoryCode = product.getCategoryCode();
        this.categoryTitle = category.getCategoryTitle();
        this.storeCode = product.getStoreCode();
        this.storeName = store.getStoreName();
        this.productDescription = product.getProductDescription();
        this.sellableStatus = product.getSellableStatus();
        this.productImage = product.getProductImage();
    }


//    public static ProductDTO from(final Product product, Category category, Store store) {
//        return new ProductDTO(
//                product.getProductCode(),
//                product.getProductName(),
//                product.getCategoryCode(),
//                category.getCategoryTitle(),
//                product.getStoreCode(),
//                store.getStoreName(),
//                product.getSellableStatus()
//        );
//    }

}
