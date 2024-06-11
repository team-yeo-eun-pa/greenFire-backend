package yep.greenFire.greenfirebackend.product.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import yep.greenFire.greenfirebackend.product.domain.type.SellableStatus;

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
    private final SellableStatus sellableStatus;

    public ProductDTO toEntity() {
        ProductDTO product = new ProductDTO(productCode, productName, categoryCode, categoryTitle, storeCode, storeName, sellableStatus);
//        product.setProductCode(this.productCode);
//        product.setProductName(this.productName);
//        product.setCategoryCode(this.categoryCode);
//        product.setCategoryTitle(this.categoryTitle);
//        product.setStoreCode(this.storeCode);
//        product.setStoreName(this.storeName);
//        product.setSellableStatus(this.sellableStatus);
        return product;
    }

}
