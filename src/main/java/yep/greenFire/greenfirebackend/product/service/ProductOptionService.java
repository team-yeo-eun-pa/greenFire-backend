package yep.greenFire.greenfirebackend.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yep.greenFire.greenfirebackend.common.exception.ConflictException;
import yep.greenFire.greenfirebackend.common.exception.NotFoundException;
import yep.greenFire.greenfirebackend.common.exception.type.ExceptionCode;
import yep.greenFire.greenfirebackend.product.domain.entity.Category;
import yep.greenFire.greenfirebackend.product.domain.entity.Product;
import yep.greenFire.greenfirebackend.product.domain.entity.ProductOption;
import yep.greenFire.greenfirebackend.product.domain.repository.ProductOptionRepository;
import yep.greenFire.greenfirebackend.product.domain.repository.ProductRepository;
import yep.greenFire.greenfirebackend.product.domain.type.ProductOptionAppearActivate;
import yep.greenFire.greenfirebackend.product.dto.request.ProductOptionCreateRequest;
import yep.greenFire.greenfirebackend.product.dto.response.ProductOptionResponse;

import java.util.Optional;

import static yep.greenFire.greenfirebackend.common.exception.type.ExceptionCode.NOT_ENOUGH_STOCK;

@Service
@RequiredArgsConstructor
public class ProductOptionService {

    private final ProductOptionRepository productOptionRepository;
    private final ProductRepository productRepository;


    /* 상품 옵션 등록 */

    private void verifyProductCreated(Long productCode) {
        if (productRepository.existsByProductCode(productCode)) {
            throw new ConflictException(ExceptionCode.NOT_FOUND_PRODUCT_CODE);
        }
    }

    public void save(final ProductOptionCreateRequest productOptionCreateRequest, Long productCode) {

        //상품 존재하는지 확인 후 저장
        verifyProductCreated(productOptionCreateRequest.getProductCode());

        Product product = productRepository.findById(productOptionCreateRequest.getProductCode())
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_PRODUCT_CODE));

        ProductOption newOption = ProductOption.of(
                product.getProductCode(),
                productOptionCreateRequest.getOptionName(),
                productOptionCreateRequest.getOptionPrice(),
                productOptionCreateRequest.getOptionStock(),
                productOptionCreateRequest.getOptionAppearActivate()
        );

        productOptionRepository.save(newOption);
    }

    /* 상품 옵션 조회 */

    @Transactional(readOnly = true)
    public Optional<ProductOption> getOptions(Long productCode) {

        return productOptionRepository.findByProductCode(productCode);
    }


    /* 상품 옵션 재고를 확인한 후 재고가 있으면 주문이 가능 (수량도 -) */
    public void updateStock(Long OptionCode, Long orderQuantity) {
        ProductOption productOption = productOptionRepository.findByOptionCodeAndOptionAppearActivate(OptionCode, ProductOptionAppearActivate.Y)
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_PRODUCT_CODE));

        verifyEnoughStock(productOption.getOptionStock(), orderQuantity);

        productOption.changeStock(orderQuantity);

    }

    private void verifyEnoughStock(Long optionStock, Long orderQuantity) {
        if(optionStock < orderQuantity) throw new ConflictException(NOT_ENOUGH_STOCK);
    }

    /* 옵션 코드로 ProductOption을 조회 */
    public ProductOption findProductOption(Long optionCode) {
        return productOptionRepository.findByOptionCodeAndOptionAppearActivate(optionCode, ProductOptionAppearActivate.Y)
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_PRODUCT_CODE));
    }
}
