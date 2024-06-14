package yep.greenFire.greenfirebackend.product.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import yep.greenFire.greenfirebackend.product.domain.type.ProductOptionAppearActivate;
import yep.greenFire.greenfirebackend.product.domain.type.SellableStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;


@Entity
@Table(name = "tbl_product")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productCode;
    private String productName;
    private Long categoryCode;
    private Long storeCode;
    private Long price;
    private String productDescription;
    @CreatedDate
    private LocalDateTime registDate;
    @Enumerated(value = EnumType.STRING)
    private SellableStatus sellableStatus = SellableStatus.Y;
    private String productImage;

    public Product(String productName, Long categoryCode, Long storeCode,
                   Long price, String productDescription,
                   SellableStatus sellableStatus, String productImage) {
        this.productName = productName;
        this.categoryCode = categoryCode;
        this.storeCode = storeCode;
        this.price = price;
        this.productDescription = productDescription;
        this.sellableStatus = sellableStatus;
        this.productImage = productImage;
    }

    public static Product of(
            final String productName, final Long categoryCode, final Long storeCode,
            final Long price, final String productDescription,
            final SellableStatus sellableStatus, final String productImage
    ) {
        return new Product(
                productName,
                categoryCode,
                storeCode,
                price,
                productDescription,
                sellableStatus,
                productImage
        );
    }

    public void modify(String productName, Long categoryCode, Long storeCode, Long price,
                              String productDescription, SellableStatus sellableStatus, String productImage
    ) {
        this.productName = productName;
        this.categoryCode = categoryCode;
        this.storeCode = storeCode;
        this.price = price;
        this.productDescription = productDescription;
        this.sellableStatus = sellableStatus;
        this.productImage = productImage;
    }


    /* 상품 삭제 (상태 변경) */
    public void modifyStatus(SellableStatus status) {
        this.sellableStatus = status;
    }


    /* 이미지 수정 */
    public void modifyProductImage(String productImage) {
        this.productImage = productImage;
    }

}
