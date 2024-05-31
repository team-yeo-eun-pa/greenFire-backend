package yep.greenFire.greenfirebackend.product.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import yep.greenFire.greenfirebackend.admin.category.domain.entity.Category;
import yep.greenFire.greenfirebackend.product.domain.type.SellableStatus;
import yep.greenFire.greenfirebackend.user.seller.domain.entity.Store;

import java.util.Date;


@Entity
@Table(name = "tbl_prouct")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productCode;
    private String productName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryCode")
    private Category category;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storeCode")
    private Store store;
    @CreatedDate
    private Date registDate;
    @Enumerated(value = EnumType.STRING)
    private SellableStatus sellableStatus = SellableStatus.SELLABLE;

    public Product(String productName, Category category,
                   Store store, Date registDate, SellableStatus sellableStatus) {
        this.productName = productName;
        this.category = category;
        this.store = store;
        this.registDate = registDate;
        this.sellableStatus = sellableStatus;
    }

    public static Product of(
            final String productName, final Category category,
            final Store store, final Date registDate, final SellableStatus sellableStatus
    ) {
        return new Product(
                productName,
                category,
                store,
                registDate,
                sellableStatus
        );
    }


}
