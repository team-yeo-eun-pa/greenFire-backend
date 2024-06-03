package yep.greenFire.greenfirebackend.inquiry.product.member.presentation;

import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yep.greenFire.greenfirebackend.common.paging.Pagination;
import yep.greenFire.greenfirebackend.common.paging.PagingButtonInfo;
import yep.greenFire.greenfirebackend.common.paging.PagingResponse;
import yep.greenFire.greenfirebackend.inquiry.product.member.dto.request.MemberProductInquiryCreatRequest;
import yep.greenFire.greenfirebackend.inquiry.product.member.dto.response.MemberProductInquiryResponse;
import yep.greenFire.greenfirebackend.inquiry.product.member.service.MemberProductInquiryService;
import yep.greenFire.greenfirebackend.inquiry.site.member.dto.request.InquiryCreateRequest;

import java.lang.reflect.Member;
import java.net.URI;

@RestController
@RequestMapping("/inquiry/product")
public class MemberProdutInquiryController {
    private MemberProductInquiryService memberProductInquiryService;

    //상품 문의 조회 ( 자기가 쓴 글만 조회 vs 전체 회원이 쓴 문의글 조회 => 고민해보기. 경로때문에.  )
    @GetMapping("/list")
    public ResponseEntity<PagingResponse> getProductInquiryContents(
            @RequestParam(defaultValue = "1") final Integer page
    ) {
        final Page<MemberProductInquiryResponse> productInquiry = memberProductInquiryService.getProductInquiryContent(1L, page);
        final PagingButtonInfo pagingButtonInfo = Pagination.getPagingButtonInfo(productInquiry);
        final PagingResponse pagingResponse = PagingResponse.of(productInquiry.getContent(), pagingButtonInfo);

        return ResponseEntity.ok(pagingResponse);
    }

    @PostMapping("/regist")
    public ResponseEntity<MemberProductInquiryResponse> save(
            @RequestPart @Valid final MemberProductInquiryCreatRequest memberProductInquiryCreateRequest
    ) {

        final int productInquiryCode = memberProductInquiryService.save(memberProductInquiryCreateRequest);
        return ResponseEntity.created(URI.create("/inquiry/product/list" + productInquiryCode)).build();
    }

}
