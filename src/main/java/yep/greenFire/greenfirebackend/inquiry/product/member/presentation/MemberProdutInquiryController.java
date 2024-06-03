package yep.greenFire.greenfirebackend.inquiry.product.member.presentation;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import yep.greenFire.greenfirebackend.common.paging.Pagination;
import yep.greenFire.greenfirebackend.common.paging.PagingButtonInfo;
import yep.greenFire.greenfirebackend.common.paging.PagingResponse;
import yep.greenFire.greenfirebackend.inquiry.product.member.dto.response.MemberProductInquiryResponse;
import yep.greenFire.greenfirebackend.inquiry.product.member.service.MemberProductInquiryService;

@RestController
@RequestMapping("/inquiry/product")
public class MemberProdutInquiryController {
    private MemberProductInquiryService memberProductInquiryService;
    @GetMapping("/list")
    public ResponseEntity<PagingResponse> getProductInquiryContents(
            @RequestParam(defaultValue = "1") final Integer page
    ) {
        final Page<MemberProductInquiryResponse> productInquiry = memberProductInquiryService.getProductInquiryContent(1L, page);
        final PagingButtonInfo pagingButtonInfo = Pagination.getPagingButtonInfo(productInquiry);
        final PagingResponse pagingResponse = PagingResponse.of(productInquiry.getContent(), pagingButtonInfo);

        return ResponseEntity.ok(pagingResponse);
    }

}
