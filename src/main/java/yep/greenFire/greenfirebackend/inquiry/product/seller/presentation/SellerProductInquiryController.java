package yep.greenFire.greenfirebackend.inquiry.product.seller.presentation;


import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yep.greenFire.greenfirebackend.common.paging.Pagination;
import yep.greenFire.greenfirebackend.common.paging.PagingButtonInfo;
import yep.greenFire.greenfirebackend.common.paging.PagingResponse;
import yep.greenFire.greenfirebackend.inquiry.product.seller.domain.repository.SellerProductInquiryRepository;
import yep.greenFire.greenfirebackend.inquiry.product.seller.dto.request.SellerProductReplyCreateRequest;
import yep.greenFire.greenfirebackend.inquiry.product.seller.dto.response.SellerProductInquiryResponse;
import yep.greenFire.greenfirebackend.inquiry.product.seller.service.SellerProductInquiryService;

import java.net.URI;

@RestController
@RequestMapping("/inquiry/product/seller")
public class SellerProductInquiryController {

    private SellerProductInquiryService sellerProductInquiryService;



    @GetMapping("/list")
    public ResponseEntity<PagingResponse> getProductInquiryList(
            @RequestParam(defaultValue = "1") final Integer page
    ) {
        final Page<SellerProductInquiryResponse> sellerProductInquiryResponse = sellerProductInquiryService.getProductInquiryList(1L, page);
        final PagingButtonInfo pagingButtonInfo = Pagination.getPagingButtonInfo(sellerProductInquiryResponse);
        final PagingResponse pagingResponse = PagingResponse.of(sellerProductInquiryResponse.getContent(), pagingButtonInfo);

        return ResponseEntity.ok(pagingResponse);
    }

    @PostMapping("/regist")
    public ResponseEntity<SellerProductInquiryResponse> save (
            @RequestPart @Valid SellerProductReplyCreateRequest sellerProductReplyCreateRequest
            ) {
        final int sellerProductReplyCode =  sellerProductInquiryService.save(sellerProductReplyCreateRequest);
        return ResponseEntity.created(URI.create("inquiry/product/seller/list" + sellerProductReplyCode)).build();
    }


    @GetMapping("/remove")
    public ResponseEntity<Void> remove (
            @RequestParam int inquiryCode
    ) {
        sellerProductInquiryService.remove(inquiryCode);

        return ResponseEntity.noContent().build();
    }




}
