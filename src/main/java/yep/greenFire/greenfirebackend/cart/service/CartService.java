package yep.greenFire.greenfirebackend.cart.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yep.greenFire.greenfirebackend.cart.domain.entity.Cart;
import yep.greenFire.greenfirebackend.cart.domain.repository.CartRepository;
import yep.greenFire.greenfirebackend.cart.dto.request.CartItemRequest;
import yep.greenFire.greenfirebackend.cart.dto.response.CartItemResponse;
import yep.greenFire.greenfirebackend.common.exception.NotFoundException;
import yep.greenFire.greenfirebackend.common.exception.type.ExceptionCode;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final CartRepository cartRepository;

    public List<CartItemResponse> getCartItems(Long memberCode) {
        return cartRepository.findByMemberCode(memberCode);
    }

    public Long save(final Long optionCode,
                     final Long cartQuantity,
                     final Long memberCode) {


        final Cart newCart = Cart.of(
                memberCode,
                optionCode,
                cartQuantity
        );

        final Cart cart = cartRepository.save(newCart);

        return cart.getCartCode();
    }


    public void modify(Long cartCode, CartItemRequest cartItemRequest) {

        Cart cart = cartRepository.findByCartCode(cartCode)
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_CART_CODE));

        cart.modify(
                cartItemRequest.getCartQuantity()
        );
    }


    public void remove(Long cartCode) {

        cartRepository.deleteById(cartCode);
    }
}

