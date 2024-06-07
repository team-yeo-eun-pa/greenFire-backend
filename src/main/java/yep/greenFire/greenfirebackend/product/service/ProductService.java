package yep.greenFire.greenfirebackend.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.web.multipart.MultipartFile;
import yep.greenFire.greenfirebackend.common.exception.ConflictException;
import yep.greenFire.greenfirebackend.common.exception.NotFoundException;
import yep.greenFire.greenfirebackend.common.exception.type.ExceptionCode;
import yep.greenFire.greenfirebackend.product.domain.entity.Category;
import yep.greenFire.greenfirebackend.product.domain.entity.Product;
import yep.greenFire.greenfirebackend.product.domain.entity.ProductOption;
import yep.greenFire.greenfirebackend.product.domain.repository.CategoryRepository;
import yep.greenFire.greenfirebackend.product.domain.repository.ProductOptionRepository;
import yep.greenFire.greenfirebackend.product.domain.repository.ProductRepository;
import yep.greenFire.greenfirebackend.product.domain.type.SellableStatus;
import yep.greenFire.greenfirebackend.product.dto.request.ProductCreateRequest;
import yep.greenFire.greenfirebackend.product.dto.request.ProductOptionCreateRequest;
import yep.greenFire.greenfirebackend.product.dto.response.ProductsResponse;
import yep.greenFire.greenfirebackend.seller.domain.entity.Store;
import yep.greenFire.greenfirebackend.store.domain.repository.StoreRepository;


@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductOptionRepository productOptionRepository;
    private final CategoryRepository categoryRepository;
    private final StoreRepository storeRepository;


//    @Value("${image.image-url}")
//    private String IMG_URL;
//    @Value("src/main/resources/static/productimg")
//    private String IMG_DIR;



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


    /* 판매자 상품 등록 */
    public Long save(final ProductCreateRequest productCreateRequest, final MultipartFile productImg) {

        // 이미지 파일 저장


        // category
        Category category = categoryRepository.findById(productCreateRequest.getCategoryCode())
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_CATEGORY_CODE));

        // store
        Store store = storeRepository.findById(productCreateRequest.getStoreCode())
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_PRODUCT_CODE));

        final Product newProduct = Product.of(
                productCreateRequest.getProductName(),
                category.getCategoryCode(),
                store.getStoreCode(),
                productCreateRequest.getPrice(),
                productCreateRequest.getRegistDate(),
                productCreateRequest.getSellableStatus()

        );

        final Product product = productRepository.save(newProduct);

        return product.getProductCode();

    }










}
