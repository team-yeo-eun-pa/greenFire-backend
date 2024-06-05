package yep.greenFire.greenfirebackend.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yep.greenFire.greenfirebackend.common.exception.ConflictException;
import yep.greenFire.greenfirebackend.common.exception.NotFoundException;
import yep.greenFire.greenfirebackend.common.exception.type.ExceptionCode;
import yep.greenFire.greenfirebackend.product.domain.entity.ProductOption;
import yep.greenFire.greenfirebackend.product.domain.repository.ProductOptionRepository;

import static yep.greenFire.greenfirebackend.common.exception.type.ExceptionCode.NOT_ENOUGH_STOCK;
import static yep.greenFire.greenfirebackend.product.domain.type.ProductOptionAppearActivate.USABLE;

@Service
@RequiredArgsConstructor
public class ProductOptionService {

    private final ProductOptionRepository productOptionRepository;

    /* 상품 옵션 재고를 확인한 후 재고가 있으면 주문이 가능 (수량도 -) */
    public void updateStock(Long OptionCode, Long orderQuantity) {
        ProductOption productOption = productOptionRepository.findByOptionCodeAndOptionAppearActivate(OptionCode, USABLE)
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_PRODUCT_CODE));

        verifyEnoughStock(productOption.getOptionStock(), orderQuantity);

        productOption.changeStock(orderQuantity);

    }

    private void verifyEnoughStock(Long optionStock, Long orderQuantity) {
        if(optionStock < orderQuantity) throw new ConflictException(NOT_ENOUGH_STOCK);
    }

    /* 옵션 코드로 ProductOption을 조회 */
    public ProductOption findProductOption(Long optionCode) {
        return productOptionRepository.findByOptionCodeAndOptionAppearActivate(optionCode, USABLE)
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_PRODUCT_CODE));
    }
}
