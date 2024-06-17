package yep.greenFire.greenfirebackend.cart.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import yep.greenFire.greenfirebackend.auth.type.CustomUser;
import yep.greenFire.greenfirebackend.cart.dto.request.CartItemRequest;
import yep.greenFire.greenfirebackend.cart.dto.response.CartItemResponse;
import yep.greenFire.greenfirebackend.cart.service.CartService;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("")
public class CartController {

    private final CartService cartService;

    @GetMapping("/cart")
    public ResponseEntity<List<CartItemResponse>> getCartItems(@AuthenticationPrincipal final CustomUser customUser) {
        List<CartItemResponse> cart = cartService.getCartItems(customUser.getMemberCode());
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PostMapping("/product")
    public ResponseEntity<Void> addCartItems(
            @RequestPart(name = "optionCode") String optionCode,
            @RequestPart(name = "cartQuantity") String cartQuantity,
            @AuthenticationPrincipal CustomUser customUser
    ) {
        try {
            Long optionCodeData = Long.parseLong(optionCode);
            Long cartQuantityData = Long.parseLong(cartQuantity);

            final Long memberCode = customUser.getMemberCode();
            cartService.save(optionCodeData, cartQuantityData, memberCode);
            return ResponseEntity.created(URI.create("/cart/"+ optionCode)).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/cart/{cartCode}")
    public ResponseEntity<Void> modify(
            @PathVariable Long cartCode,
            @RequestParam @Valid final CartItemRequest cartItemRequest
    ) {
        cartService.modify(cartCode, cartItemRequest);

        return ResponseEntity.created(URI.create("/cart/"+ cartCode)).build();
    }

    @DeleteMapping("/cart/{cartCode}")
    public ResponseEntity<Void> remove(@PathVariable final Long cartCode) {

        cartService.remove(cartCode);

        return ResponseEntity.noContent().build();
    }

}
