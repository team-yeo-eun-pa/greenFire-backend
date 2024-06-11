package yep.greenFire.greenfirebackend.product.dto.response;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import yep.greenFire.greenfirebackend.product.domain.entity.Product;
import yep.greenFire.greenfirebackend.product.domain.type.SellableStatus;
import java.util.Date;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class SellerProductsResponse {

    private final Long productCode;
    private final String productName;
    private final Long categoryCode;
    private final Long storeCode;
    private final Long price;
    private final Date registDate;
    private final SellableStatus sellableStatus;


    public static SellerProductsResponse from(final Product product) {
        return new SellerProductsResponse(
                product.getProductCode(),
                product.getProductName(),
                product.getCategoryCode(),
                product.getStoreCode(),
                product.getPrice(),
                product.getRegistDate(),
                product.getSellableStatus()
        );

    }

    public SellerProductsResponse(Product product) {
        this.productCode = product.getProductCode();
        this.productName = product.getProductName();
        this.categoryCode = product.getCategoryCode();
        this.storeCode = product.getStoreCode();
        this.price = product.getPrice();
        this.registDate = product.getRegistDate();
        this.sellableStatus = product.getSellableStatus();
    }
}
