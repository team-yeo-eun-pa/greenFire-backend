package yep.greenFire.greenfirebackend.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import yep.greenFire.greenfirebackend.common.exception.NotFoundException;
import yep.greenFire.greenfirebackend.common.exception.type.ExceptionCode;
import yep.greenFire.greenfirebackend.product.domain.entity.Product;
import yep.greenFire.greenfirebackend.product.domain.repository.CategoryRepository;
import yep.greenFire.greenfirebackend.product.domain.repository.ProductOptionRepository;
import yep.greenFire.greenfirebackend.product.domain.repository.ProductRepository;
import yep.greenFire.greenfirebackend.product.domain.type.SellableStatus;
import yep.greenFire.greenfirebackend.product.dto.response.AdminCategoryResponse;
import yep.greenFire.greenfirebackend.product.dto.response.ProductResponse;
import yep.greenFire.greenfirebackend.product.dto.response.ProductsResponse;
import yep.greenFire.greenfirebackend.product.dto.response.SellerProductsResponse;
import yep.greenFire.greenfirebackend.store.domain.entity.Store;
import yep.greenFire.greenfirebackend.store.domain.repository.StoreRepository;

import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductOptionRepository productOptionRepository;
    private final StoreRepository storeRepository;




    private Pageable getPageable(final Integer page) {
        return PageRequest.of(page - 1, 10, Sort.by("productCode").descending());
    }

    /* 상품 목록 조회 */

    @Transactional(readOnly = true)
    public Page<ProductsResponse> getProducts(final Integer page, final Long categoryCode,
                                              final Long storeCode, final String productName) {
        Page<ProductsResponse> products = null;
        if (categoryCode != null && categoryCode > 0) {
            return productRepository.findByCategoryCodeAndSellableStatus(getPageable(page), categoryCode, SellableStatus.Y);
        } else if (storeCode != null && storeCode > 0) {
            return productRepository.findByStoreCodeAndSellableStatus(getPageable(page), storeCode, SellableStatus.Y);
        } else if (productName != null && !productName.isEmpty()) {
            return productRepository.findByProductNameContainsAndSellableStatus(getPageable(page), productName, SellableStatus.Y);
        } else {
            return productRepository.findBySellableStatus(getPageable(page), SellableStatus.Y);
        }
    }

    /* 상품 상세 조회 */

    @Transactional(readOnly = true)
    public ProductResponse getProduct(final Long productCode) {

        ProductResponse productResponse = productRepository.findByProductCodeAndSellableStatus(productCode, SellableStatus.Y)
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_PRODUCT_CODE));

        return productResponse;
    }

    /* 마이스토어 상품 목록 조회 */
    @Transactional(readOnly = true)
    public Page<SellerProductsResponse> getSellerProducts(final Integer page, final Long memberCode) {

        /* 현재 로그인한 memberCode와 일치하는 storeCode 찾기 */
//        Long storeCode = storeRepository.findStoreByMemberCode(memberCode);

        /* 스토어 코드와 일치하는 상품 목록 찾기 */
        Page<SellerProductsResponse> sellerProducts = productRepository.findByMemberCode(getPageable(page), memberCode);
//        Optional<AdminCategoryResponse> categories = CategoryRepository.

//        return sellerProducts.map(ProductsResponse::toSellerProductsResponse);
        return sellerProducts;

    }


}









