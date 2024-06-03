package yep.greenFire.greenfirebackend.product.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import yep.greenFire.greenfirebackend.common.paging.Pagination;
import yep.greenFire.greenfirebackend.common.paging.PagingButtonInfo;
import yep.greenFire.greenfirebackend.common.paging.PagingResponse;
import yep.greenFire.greenfirebackend.product.dto.response.ProductsResponse;
import yep.greenFire.greenfirebackend.product.service.ProductService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    /* 상품 목록 조회 */
    /* 이미지, 옵션 참조 */
    @GetMapping("")
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


}
