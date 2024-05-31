package yep.greenFire.greenfirebackend.product.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import yep.greenFire.greenfirebackend.admin.category.domain.entity.Category;
import yep.greenFire.greenfirebackend.product.domain.type.SellableStatus;
import yep.greenFire.greenfirebackend.user.seller.domain.entity.Store;

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
    @CreatedDate
    private Date registDate;
    @Enumerated(value = EnumType.STRING)
    private SellableStatus sellableStatus = SellableStatus.SELLABLE;

    public Product(String productName, Long categoryCode,
                   Long storeCode, Date registDate, SellableStatus sellableStatus) {
        this.productName = productName;
        this.categoryCode = categoryCode;
        this.storeCode = storeCode;
        this.registDate = registDate;
        this.sellableStatus = sellableStatus;
    }

    public static Product of(
            final String productName, final Long categoryCode,
            final Long storeCode, final Date registDate, final SellableStatus sellableStatus
    ) {
        return new Product(
                productName,
                categoryCode,
                storeCode,
                registDate,
                sellableStatus
        );
    }


}
