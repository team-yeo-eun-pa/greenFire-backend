package yep.greenFire.greenfirebackend.cart.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import yep.greenFire.greenfirebackend.cart.domain.entity.Cart;
import yep.greenFire.greenfirebackend.product.domain.entity.Product;
import yep.greenFire.greenfirebackend.product.domain.entity.ProductOption;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class CartItemResponse {

    private final Long cartCode;
    private final Long memberCode;
    private final Long optionCode;
    private final String optionName;
    private final Long optionPrice;
    private final Long productCode;
    private final String productName;
    private final Long cartQuantity;
    private final String productImg;


    public CartItemResponse(Cart cart, ProductOption option, Product product) {
        this.cartCode = cart.getCartCode();
        this.memberCode = cart.getMemberCode();
        this.optionCode = cart.getOptionCode();
        this.optionName = option.getOptionName();
        this.optionPrice = option.getOptionPrice();
        this.productCode = product.getProductCode();
        this.productName = product.getProductName();
        this.cartQuantity = cart.getCartQuantity();
        this.productImg = product.getProductImg();
    }

    public static CartItemResponse from(final Cart cart, ProductOption option, Product product) {
        return new CartItemResponse(
                cart.getCartCode(),
                cart.getMemberCode(),
                cart.getOptionCode(),
                option.getOptionName(),
                option.getOptionPrice(),
                product.getProductCode(),
                product.getProductName(),
                cart.getCartQuantity(),
                product.getProductImg()
        );

    }
}

