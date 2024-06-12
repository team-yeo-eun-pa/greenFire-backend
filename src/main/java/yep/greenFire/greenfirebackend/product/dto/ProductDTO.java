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
    private final Long storeCode;
    private final SellableStatus sellableStatus;


    public static ProductDTO from(final Product product) {
        return new ProductDTO(
                product.getProductCode(),
                product.getProductName(),
                product.getCategoryCode(),
                product.getStoreCode(),
                product.getSellableStatus()
        );
    }

}
