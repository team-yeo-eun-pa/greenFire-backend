package yep.greenFire.greenfirebackend.product.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import yep.greenFire.greenfirebackend.product.domain.type.ProductOptionAppearActivate;

@Entity
@Table(name = "tbl_product_option")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EntityListeners(AuditingEntityListener.class)
public class ProductOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long optionCode;
    private Long productCode;
    private String optionName;
    private Long optionPrice;
    private Long optionStock;


    public ProductOption(Long productCode, String optionName, Long optionPrice,
                         Long optionStock, ProductOptionAppearActivate optionAppearActivate) {
        this.productCode = productCode;
        this.optionName = optionName;
        this.optionPrice = optionPrice;
        this.optionStock = optionStock;
        this.optionAppearActivate = optionAppearActivate;
    }

    /* 상품 옵션 조회 가능 여부 */
    @Enumerated(value = EnumType.STRING)
    private ProductOptionAppearActivate optionAppearActivate = ProductOptionAppearActivate.Y;


    /* 주문 시 재고 수량 수정 */
    public void changeStock(Long orderQuantity) {
        this.optionStock -= orderQuantity;
    }
}
