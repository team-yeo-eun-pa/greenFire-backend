package yep.greenFire.greenfirebackend.admin.category.domain.entity;


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

    public Category(Long categoryCode, String categoryTitle, Long refCategoryCode) {
        this.categoryCode=categoryCode;
        this.categoryTitle=categoryTitle;
        this.refCategoryCode=refCategoryCode;
    }

    public static Category of(final Long categoryCode, final String categoryTitle, final Long refCategoryCode) {
        return new Category(
                categoryCode,
                categoryTitle,
                refCategoryCode
        );
    }

    public void modify(final String categoryTitle, final Long refCategoryCode) {
        this.categoryCode=categoryCode;
        this.categoryTitle=categoryTitle;
        this.refCategoryCode=refCategoryCode;
    }
}
