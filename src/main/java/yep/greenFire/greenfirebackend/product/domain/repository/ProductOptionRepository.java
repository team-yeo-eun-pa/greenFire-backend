package yep.greenFire.greenfirebackend.product.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import yep.greenFire.greenfirebackend.product.domain.entity.ProductOption;
import yep.greenFire.greenfirebackend.product.domain.type.ProductOptionAppearActivate;

import java.util.Optional;

@Repository
public interface ProductOptionRepository extends JpaRepository<ProductOption, Long> {

    /* 상품 상세 조회 : OptionCode로 상품 1개 조회, 옵션 조회 불가 상품 제외 (고객) */
    Optional<ProductOption> findByOptionCodeAndOptionAppearActivate(Long OptionCode, ProductOptionAppearActivate productOptionAppearActivate);

    Optional<ProductOption> findByProductCode(Long productCode);
}
