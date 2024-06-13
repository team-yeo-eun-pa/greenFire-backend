package yep.greenFire.greenfirebackend.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.web.bind.annotation.PutMapping;
import yep.greenFire.greenfirebackend.common.exception.NotFoundException;
import yep.greenFire.greenfirebackend.common.exception.type.ExceptionCode;
import yep.greenFire.greenfirebackend.product.domain.entity.Category;
import yep.greenFire.greenfirebackend.product.domain.entity.Product;
import yep.greenFire.greenfirebackend.product.domain.entity.ProductOption;
import yep.greenFire.greenfirebackend.product.domain.repository.CategoryRepository;
import yep.greenFire.greenfirebackend.product.domain.repository.ProductOptionRepository;
import yep.greenFire.greenfirebackend.product.domain.repository.ProductRepository;
import yep.greenFire.greenfirebackend.product.domain.type.ProductOptionAppearActivate;
import yep.greenFire.greenfirebackend.product.domain.type.SellableStatus;
import yep.greenFire.greenfirebackend.product.dto.ProductDTO;
import yep.greenFire.greenfirebackend.product.dto.ProductOptionDTO;
import yep.greenFire.greenfirebackend.product.dto.request.ProductDeleteRequest;
import yep.greenFire.greenfirebackend.product.dto.request.ProductOptionDeleteRequest;
import yep.greenFire.greenfirebackend.product.dto.response.*;
import yep.greenFire.greenfirebackend.store.domain.entity.Store;
import yep.greenFire.greenfirebackend.store.domain.repository.StoreRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static yep.greenFire.greenfirebackend.product.domain.type.SellableStatus.D;


@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductOptionRepository productOptionRepository;
    private final CategoryRepository categoryRepository;
    private final StoreRepository storeRepository;


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

        ProductDTO product = productRepository.findByProductCodeAndSellableStatus(productCode, SellableStatus.Y)
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_PRODUCT_CODE));

        List<ProductOption> productOptions = productOptionRepository.findByProductCodeAndOptionAppearActivate(productCode, ProductOptionAppearActivate.Y);


        return ProductResponse.of(product, productOptions.stream().map(productOption -> ProductOptionDTO.from(productOption)).collect(Collectors.toList()));

    }


    /* 마이스토어 상품 목록 조회 */
    @Transactional(readOnly = true)
    public Page<SellerProductsResponse> getSellerProducts(final Integer page, final Long memberCode) {

        /* 현재 로그인한 memberCode와 일치하는 storeCode 찾기 */
//        Long storeCode = storeRepository.findStoreByMemberCode(memberCode);

        /* 스토어 코드와 일치하는 상품 목록 찾기 */
        Page<SellerProductsResponse> sellerProducts = productRepository.findByMemberCodeAndSellableStatusNot(getPageable(page), memberCode, D);

        return sellerProducts;

    }


    /* 상품 삭제 */

    public void modifyStatus(Long productCode, ProductDeleteRequest productDeleteRequest) {

        Product product = productRepository.findByProductCodeAndSellableStatusNot(productCode, D)
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_PRODUCT_CODE));

        List<ProductOption> productOptions = productOptionRepository.findByProductCode(productCode);

        productOptions.forEach(productOption -> productOption.modifyStatus(ProductOptionAppearActivate.D));

        product.modifyStatus(
                productDeleteRequest.getSellablestatus()
        );
    }




}


    /* 판매자 상품 등록 */
    private String getRandomName() { return UUID.randomUUID().toString().replace("-", ""); }

    public Long save(final ProductCreateRequest productCreateRequest,
                     final MultipartFile productImg) {

        // 이미지 파일 저장
        String replaceFileName = FileUploadUtils.saveFile(IMG_DIR, getRandomName(), productImg);


        // category
        Category category = categoryRepository.findById(productCreateRequest.getCategoryCode())
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_CATEGORY_CODE));

        // store 로그인한 회원 슽토어 맞는지 확인 추가 +memberCode
//        Store store = storeRepository.findById(productCreateRequest.getStoreCode())
//                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_PRODUCT_CODE));


//        final Product newProduct = Product.of(
//                productCreateRequest.getProductName(),
//                category.getCategoryCode(),
//                productCreateRequest.getStoreCode(),
//                productCreateRequest.getPrice(),
//                productCreateRequest.getProductDescription(),
//                productCreateRequest.getRegistDate(),
//                productCreateRequest.getSellableStatus(),
//                IMG_URL + replaceFileName
//
//        );

        final Product product = productRepository.save(newProduct);

        return product.getProductCode();
        }







