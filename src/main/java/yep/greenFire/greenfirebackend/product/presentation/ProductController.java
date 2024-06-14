package yep.greenFire.greenfirebackend.product.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import yep.greenFire.greenfirebackend.auth.type.CustomUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import yep.greenFire.greenfirebackend.auth.type.CustomUser;
import yep.greenFire.greenfirebackend.common.paging.Pagination;
import yep.greenFire.greenfirebackend.common.paging.PagingButtonInfo;
import yep.greenFire.greenfirebackend.common.paging.PagingResponse;
import yep.greenFire.greenfirebackend.product.dto.request.*;
import yep.greenFire.greenfirebackend.product.dto.response.ProductResponse;
import yep.greenFire.greenfirebackend.product.dto.response.ProductsResponse;
import yep.greenFire.greenfirebackend.product.dto.response.SellerProductsResponse;
import yep.greenFire.greenfirebackend.product.service.ProductOptionService;
import yep.greenFire.greenfirebackend.product.service.ProductService;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("")
public class ProductController {

    @Autowired
    private final ProductService productService;


    /* 상품 목록 조회 */
    /* 이미지 참조 */
    @GetMapping("/product")
    public ResponseEntity<PagingResponse> getProducts(
            @RequestParam(defaultValue = "1") final Integer page,
            @RequestParam(required = false) final Long categoryCode,
            @RequestParam(required = false) final Long storeCode,
            @RequestParam(required = false) final String productName
    ) {
        final Page<ProductsResponse> products = productService.getProducts(page, categoryCode, storeCode, productName);
        final PagingButtonInfo pagingButtonInfo = Pagination.getPagingButtonInfo(products);
        final PagingResponse pagingResponse = PagingResponse.of(products.getContent(), pagingButtonInfo);

        return ResponseEntity.ok(pagingResponse);
    }

    /* 상품 상세 조회 */
    @GetMapping("/product/{productCode}")
    @ResponseBody
    public ResponseEntity<ProductResponse> getProduct(@PathVariable final Long productCode) {

        final ProductResponse productResponse = productService.getProduct(productCode);

        return ResponseEntity.ok(productResponse);
    }

    /* 판매자 상품 목록 조회 */
    @GetMapping("/seller/mystore/product")
    public ResponseEntity<PagingResponse> getSellerProducts(
            @RequestParam(defaultValue = "1") final Integer page,
            @AuthenticationPrincipal final CustomUser customUser
    ) {
        final Long memberCode = customUser.getMemberCode();
        final Page<SellerProductsResponse> sellerProducts = productService.getSellerProducts(page, memberCode);
        final PagingButtonInfo pagingButtonInfo = Pagination.getPagingButtonInfo(sellerProducts);
        final PagingResponse pagingResponse = PagingResponse.of(sellerProducts.getContent(), pagingButtonInfo);

        return ResponseEntity.ok(pagingResponse);
    }

    /* 판매자 상품 등록 */
    @PostMapping("seller/mystore/regist")
    public ResponseEntity<Void> save(
            @RequestPart @Valid final ProductCreateRequest productCreateRequest,
            @RequestPart @Valid final List<ProductOptionCreateRequest> productOptionCreateRequest,
            @RequestPart final MultipartFile productImg,
            @AuthenticationPrincipal final CustomUser customUser
            ) {
        try {
            final Long memberCode = customUser.getMemberCode();
            final Long productCode = productService.save(productCreateRequest, productImg, productOptionCreateRequest, memberCode);
            return ResponseEntity.created(URI.create("/seller/mystore/regist/" + productCode)).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    /* 상품 수정 */
    @PutMapping("/seller/mystore/edit/{productCode}")
    public ResponseEntity<Void> modifyProduct(
            @PathVariable final Long productCode,
            @RequestPart @Valid final ProductUpdateRequest productUpdateRequest,
            @AuthenticationPrincipal CustomUser customUser,
            @RequestPart(required = false) final MultipartFile productImage
    ) {
        final Long memberCode = customUser.getMemberCode();
        productService.modifyProduct(productCode, memberCode, productUpdateRequest, productImage);

        return ResponseEntity.created(URI.create("/seller/mystore/edit/" + productCode)).build();
    }

    /* 상품 옵션 수정 */

    /* 판매자 상품 삭제 -> 상태 변경 */
    /* 상품 상태 변경 이전에 옵션 먼저 변경 필요 */
    @PutMapping("/seller/mystore/product/{productCode}")
    public ResponseEntity<Void> modifyStatus(
            @PathVariable final Long productCode,
            @RequestBody @Valid final ProductDeleteRequest productDeleteRequest
    ) {
        productService.modifyStatus(productCode, productDeleteRequest);

        return ResponseEntity.created(URI.create("seller/mystore/product/" + productCode)).build();
    }




}