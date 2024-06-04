package yep.greenFire.greenfirebackend.product.domain.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "tbl_category")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryCode;
    private String categoryTitle;
    private Long refCategoryCode;

    public Category(String categoryTitle, Long refCategoryCode) {
        this.categoryTitle=categoryTitle;
        this.refCategoryCode=refCategoryCode;
    }

    public static Category of( final String categoryTitle, final Long refCategoryCode) {
        return new Category(
                categoryTitle,
                refCategoryCode
        );
    }

    public void modify(final String categoryTitle, final Long refCategoryCode) {
        this.categoryTitle=categoryTitle;
        this.refCategoryCode=refCategoryCode;
    }
}
