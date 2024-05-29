package yep.greenFire.greenfirebackend.product.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yep.greenFire.greenfirebackend.product.domain.entity.ProductOption;
import yep.greenFire.greenfirebackend.product.domain.type.ProductOptionAppearActivate;

import java.util.Optional;

public interface ProductOptionRepository extends JpaRepository<ProductOption, Long> {

    /* 5. 상품 상세 조회 : OptionCode로 상품 1개 조회, 옵션 조회 불가 상품 제외 (고객) */
    Optional<ProductOption> findByOptionCodeAndOptionAppearActivate(Long OptionCode, ProductOptionAppearActivate productOptionAppearActivate);

}
