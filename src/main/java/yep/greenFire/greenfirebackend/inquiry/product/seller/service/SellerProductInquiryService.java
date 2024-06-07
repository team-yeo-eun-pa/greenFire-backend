package yep.greenFire.greenfirebackend.inquiry.product.seller.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import yep.greenFire.greenfirebackend.inquiry.entity.InquiryContent;
import yep.greenFire.greenfirebackend.inquiry.product.seller.domain.repository.SellerProductInquiryRepository;
import yep.greenFire.greenfirebackend.inquiry.product.seller.dto.request.SellerProductReplyCreateRequest;
import yep.greenFire.greenfirebackend.inquiry.product.seller.dto.response.SellerProductInquiryResponse;

@Service
public class SellerProductInquiryService {

    private SellerProductInquiryRepository sellerProductInquiryRepository;
    private Pageable getPageable(final Integer page) {
        return PageRequest.of(page - 1, 10, Sort.by("inquiryCode").descending());
    }


//    public Page<SellerProductInquiryResponse> getProductInquiryList(long productCode, Integer page) {
//        Page<InquiryContent> sellerProductInquiry = sellerProductInquiryRepository.findByProductCode(productCode, getPageable(page));
//
//        return sellerProductInquiry.map(SellerProductInquiryResponse::from);
//
//    }

//    public int save(SellerProductReplyCreateRequest sellerProductReplyCreateRequest) {
//
//        final InquiryContent newProductInquiryReply = InquiryContent.of4(
//                sellerProductReplyCreateRequest.getProductCode(),
//                sellerProductReplyCreateRequest.getProductName(),
//                sellerProductReplyCreateRequest.getMemberCode(),
//                sellerProductReplyCreateRequest.getInquiryTitle(),
//                sellerProductReplyCreateRequest.getInquiryDetail(),
//                sellerProductReplyCreateRequest.getInquiryWriteDate(),
//                sellerProductReplyCreateRequest.getInquiryReply(),
//                sellerProductReplyCreateRequest.getInquiryReplyStatus()
//
//        );
//
//        final InquiryContent newProductReply = sellerProductInquiryRepository.save(newProductInquiryReply);
//        return newProductReply.getInquiryCode();
//
//    }

    public void remove(int inquiryCode) {

        sellerProductInquiryRepository.deleteById(inquiryCode);
    }
}
