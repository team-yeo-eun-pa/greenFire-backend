package yep.greenFire.greenfirebackend.product.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
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
    @CreatedDate
    private Date registDate;
    @Enumerated(value = EnumType.STRING)
    private SellableStatus sellableStatus = SellableStatus.Y;
    private String productImg;

    public Product(String productName, Long categoryCode, Long storeCode,
                   Long price, Date registDate, SellableStatus sellableStatus) {
        this.productName = productName;
        this.categoryCode = categoryCode;
        this.storeCode = storeCode;
        this.price = price;
        this.registDate = registDate;
        this.sellableStatus = sellableStatus;
    }

    public static Product of(
            final String productName, final Long categoryCode, final Long storeCode,
            final Long price, final Date registDate, final SellableStatus sellableStatus
    ) {
        return new Product(
                productName,
                categoryCode,
                storeCode,
                price,
                registDate,
                sellableStatus
        );
    }

}
