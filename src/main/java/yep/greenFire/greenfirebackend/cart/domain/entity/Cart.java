package yep.greenFire.greenfirebackend.cart.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "tbl_cart")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartCode;
    private Long memberCode;
    private Long optionCode;
    private Long cartQuantity;


    public Cart(Long cartCode, Long memberCode, Long optionCode, Long cartQuantity) {
        this.cartCode = cartCode;
        this.memberCode = memberCode;
        this.optionCode = optionCode;
        this.cartQuantity = cartQuantity;
    }
}
