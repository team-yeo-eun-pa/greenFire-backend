package yep.greenFire.greenfirebackend.product.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import yep.greenFire.greenfirebackend.common.paging.Pagination;
import yep.greenFire.greenfirebackend.common.paging.PagingButtonInfo;
import yep.greenFire.greenfirebackend.common.paging.PagingResponse;
import yep.greenFire.greenfirebackend.product.dto.request.ProductCreateRequest;
import yep.greenFire.greenfirebackend.product.dto.request.ProductOptionCreateRequest;
import yep.greenFire.greenfirebackend.product.dto.response.ProductsResponse;
import yep.greenFire.greenfirebackend.product.service.ProductOptionService;
import yep.greenFire.greenfirebackend.product.service.ProductService;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class ProductController {

    private final ProductService productService;
    private final ProductOptionService productOptionService;

    /* 상품 목록 조회 */
    /* 이미지, 옵션 참조 */
    @GetMapping("product")
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

    /* 판매자 상품 등록 */
    @PostMapping("seller/mystore/regist")
    public ResponseEntity<Void> save(
            @RequestPart @Valid final ProductCreateRequest productCreateRequest,
            @RequestPart @Valid final ProductOptionCreateRequest productOptionCreateRequest,
            @RequestPart final MultipartFile productImg
            ) {
        final Long productCode = productService.save(productCreateRequest, productImg);
        productOptionService.save(productOptionCreateRequest, productCode);
        return ResponseEntity.created(URI.create("/seller/mystore/regist/" + productCode)).build();
    }


}
