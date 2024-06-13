package yep.greenFire.greenfirebackend.product.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import yep.greenFire.greenfirebackend.product.domain.type.ProductOptionAppearActivate;
import yep.greenFire.greenfirebackend.product.domain.type.SellableStatus;

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
    private Date registDate;
    @Enumerated(value = EnumType.STRING)
    private SellableStatus sellableStatus = SellableStatus.Y;
    private String productImageUrl;

    public Product(Long productCode, String productName, Long categoryCode, Long storeCode,
                   Long price, String productDescription, Date registDate,
                   SellableStatus sellableStatus, String productImageUrl) {
        this.productCode = productCode;
        this.productName = productName;
        this.categoryCode = categoryCode;
        this.storeCode = storeCode;
        this.price = price;
        this.productDescription = productDescription;
        this.registDate = registDate;
        this.sellableStatus = sellableStatus;
        this.productImageUrl = productImageUrl;
    }

    public static Product of(
            final Long productCode, final String productName, final Long categoryCode, final Long storeCode,
            final Long price, final String productDescription, final Date registDate,
            final SellableStatus sellableStatus, final String productImageUrl
    ) {
        return new Product(
                productCode,
                productName,
                categoryCode,
                storeCode,
                price,
                productDescription,
                registDate,
                sellableStatus,
                productImageUrl
        );
    }

    /* 상품 삭제 */
    public void modifyStatus(SellableStatus status) {
        this.sellableStatus = status;
    }

}
