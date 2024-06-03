package yep.greenFire.greenfirebackend.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yep.greenFire.greenfirebackend.admin.category.domain.entity.Category;
import yep.greenFire.greenfirebackend.product.domain.entity.Product;
import yep.greenFire.greenfirebackend.product.domain.repository.ProductOptionRepository;
import yep.greenFire.greenfirebackend.product.domain.repository.ProductRepository;
import yep.greenFire.greenfirebackend.product.domain.type.SellableStatus;
import yep.greenFire.greenfirebackend.product.dto.response.ProductsResponse;
import yep.greenFire.greenfirebackend.user.seller.domain.entity.Store;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductOptionRepository productOptionRepository;


    @Value("${image.image-url}")
    private String IMG_URL;
    @Value("src/main/resources/static/productimg")
    private String IMG_DIR;



    private Pageable getPageable(final Integer page) {
        return PageRequest.of(page - 1, 10, Sort.by("productCode").descending());
    }

    /* 상품 목록 조회 */
    @Transactional(readOnly = true)
    public Page<ProductsResponse> getProducts(final Integer page, final Long categoryCode,
                                              final Long storeCode, final String productName) {
        Page<Product> products = null;
        if (categoryCode != null && categoryCode > 0) {
            products = productRepository.findByCategoryCodeAndSellableStatus(getPageable(page), categoryCode, SellableStatus.Y);
        } else if (storeCode != null && storeCode > 0) {
            products = productRepository.findByStoreCodeAndSellableStatus(getPageable(page), storeCode, SellableStatus.Y);
        } else if (productName != null && !productName.isEmpty()) {
            products = productRepository.findByProductNameContainsAndSellableStatus(getPageable(page), productName, SellableStatus.Y);
        } else {
            products = productRepository.findBySellableStatus(getPageable(page), SellableStatus.Y);
        }
        return products.map(ProductsResponse::from);
    }








}
