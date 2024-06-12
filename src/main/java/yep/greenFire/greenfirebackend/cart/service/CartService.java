package yep.greenFire.greenfirebackend.cart.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yep.greenFire.greenfirebackend.cart.domain.entity.Cart;
import yep.greenFire.greenfirebackend.cart.domain.repository.CartRepository;
import yep.greenFire.greenfirebackend.cart.dto.response.CartItemResponse;
import yep.greenFire.greenfirebackend.product.domain.entity.Category;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final CartRepository cartRepository;

    public List<CartItemResponse> getCartItems(Long memberCode) {
        return cartRepository.findByMemberCode(memberCode);
    }
}
