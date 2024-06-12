package yep.greenFire.greenfirebackend.cart.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import yep.greenFire.greenfirebackend.auth.type.CustomUser;
import yep.greenFire.greenfirebackend.cart.domain.entity.Cart;
import yep.greenFire.greenfirebackend.cart.dto.response.CartItemResponse;
import yep.greenFire.greenfirebackend.cart.service.CartService;
import yep.greenFire.greenfirebackend.common.paging.Pagination;
import yep.greenFire.greenfirebackend.common.paging.PagingButtonInfo;
import yep.greenFire.greenfirebackend.common.paging.PagingResponse;
import yep.greenFire.greenfirebackend.product.domain.entity.Category;
import yep.greenFire.greenfirebackend.product.dto.response.ProductsResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @GetMapping("")
    public ResponseEntity<List<CartItemResponse>> getCartItems(@AuthenticationPrincipal final CustomUser customUser) {
        List<CartItemResponse> cart = cartService.getCartItems(customUser.getMemberCode());
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }
}
