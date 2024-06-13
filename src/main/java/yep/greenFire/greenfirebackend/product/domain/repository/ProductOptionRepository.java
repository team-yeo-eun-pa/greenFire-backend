package yep.greenFire.greenfirebackend.product.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import yep.greenFire.greenfirebackend.product.domain.entity.ProductOption;
import yep.greenFire.greenfirebackend.product.domain.type.ProductOptionAppearActivate;
import yep.greenFire.greenfirebackend.product.dto.ProductOptionDTO;
import yep.greenFire.greenfirebackend.product.dto.response.ProductOptionResponse;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductOptionRepository extends JpaRepository<ProductOption, Long> {

    /* 상품 상세 조회 : OptionCode로 상품 1개 조회, 옵션 조회 불가 상품 제외 (고객) */
    Optional<ProductOption> findByOptionCodeAndOptionAppearActivate(Long OptionCode, ProductOptionAppearActivate productOptionAppearActivate);

    /* 상품 상세 페이지 옵션 조회 */
    List<ProductOption> findByProductCodeAndOptionAppearActivate(Long productCode, ProductOptionAppearActivate productOptionAppearActivate);

    /* 옵션 삭제 */
    List<ProductOption> findByProductCode(Long productCode);


}
