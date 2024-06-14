package yep.greenFire.greenfirebackend.product.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import yep.greenFire.greenfirebackend.product.domain.entity.Category;
import yep.greenFire.greenfirebackend.product.domain.entity.Product;
import yep.greenFire.greenfirebackend.product.domain.type.SellableStatus;
import yep.greenFire.greenfirebackend.product.dto.ProductDTO;
import yep.greenFire.greenfirebackend.product.dto.ProductOptionDTO;
import yep.greenFire.greenfirebackend.store.domain.entity.Store;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class SellerProductResponse {

    private ProductDTO productInfo;
    private List<ProductOptionDTO> productOptions;


    public SellerProductResponse(ProductDTO productInfo, List<ProductOptionDTO> productOptions) {
        this.productInfo = productInfo;
        this.productOptions = productOptions;

    }

    public static SellerProductResponse of(final ProductDTO productInfo, List<ProductOptionDTO> productOptions) {
        return new SellerProductResponse(
                productInfo,
                productOptions
        );
    }


}
