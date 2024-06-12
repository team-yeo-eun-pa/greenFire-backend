package yep.greenFire.greenfirebackend.product.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import yep.greenFire.greenfirebackend.product.domain.entity.Category;
import yep.greenFire.greenfirebackend.product.domain.entity.Product;
import yep.greenFire.greenfirebackend.product.domain.entity.ProductOption;
import yep.greenFire.greenfirebackend.product.domain.type.ProductOptionAppearActivate;
import yep.greenFire.greenfirebackend.product.domain.type.SellableStatus;
import yep.greenFire.greenfirebackend.product.dto.ProductDTO;
import yep.greenFire.greenfirebackend.product.dto.ProductOptionDTO;
import yep.greenFire.greenfirebackend.store.domain.entity.Store;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductResponse {

    private ProductDTO productInfo;
    private List<ProductOptionDTO> productOptions;


    public ProductResponse(ProductDTO productInfo, List<ProductOptionDTO> productOptions) {
        this.productInfo = productInfo;
        this.productOptions = productOptions;

    }

    public static ProductResponse of(final ProductDTO productInfo, List<ProductOptionDTO> productOptions) {
        return new ProductResponse(
                productInfo,
                productOptions
        );
    }

    //    public static ProductResponse from(final Product product, Category category, Store store) {
//        return new ProductResponse(
//                product.getProductCode(),
//                product.getProductName(),
//                product.getCategoryCode(),
//                category.getCategoryTitle(),
//                product.getStoreCode(),
//                store.getStoreName(),
//                product.getSellableStatus()
//        );
//    }
//
//    public ProductResponse(final Product product, Category category, Store store, ProductOption productOption) {
//        this.productCode = product.getProductCode();
//        this.productName = product.getProductName();
//        this.categoryCode = product.getCategoryCode();
//        this.categoryTitle = category.getCategoryTitle();
//        this.storeCode = product.getStoreCode();
//        this.storeName = store.getStoreName();
//        this.sellableStatus = product.getSellableStatus();
//        this.optionCode =productOption.getOptionCode();
//        this.optionName = productOption.getOptionName();
//        this.productOptionAppearActivate = productOption.getOptionAppearActivate();
//        this.optionPrice = productOption.getOptionPrice();
//        this.optionStock = productOption.getOptionStock();
//    }
}
