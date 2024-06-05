package yep.greenFire.greenfirebackend.inquiry.product.member.service;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestPart;
import yep.greenFire.greenfirebackend.inquiry.entity.InquiryContent;
import yep.greenFire.greenfirebackend.inquiry.product.member.domain.repository.MemberProductInquiryRepository;
import yep.greenFire.greenfirebackend.inquiry.product.member.dto.request.MemberProductInquiryCreatRequest;
import yep.greenFire.greenfirebackend.inquiry.product.member.dto.response.MemberProductInquiryResponse;

import java.util.Date;


@Service
public class MemberProductInquiryService {
    private MemberProductInquiryRepository memberProductInquiryRepository;


    private Pageable getPageable(final Integer page) {
        return PageRequest.of(page - 1, 10, Sort.by("inquiryCode").descending());
    }
    public Page<MemberProductInquiryResponse> getProductInquiryContent(long productCode, Integer page) {

        Page<InquiryContent> productInquiry = memberProductInquiryRepository.findByProductCode(productCode, getPageable(page));

        return productInquiry.map(MemberProductInquiryResponse::from);

    }


    public int save(
            @RequestPart @Valid final MemberProductInquiryCreatRequest memberProductInquiryCreateRequest) {

        final InquiryContent newProductInquiry = InquiryContent.of3(
                memberProductInquiryCreateRequest.getProductCode(),
                memberProductInquiryCreateRequest.getProductName(),
                memberProductInquiryCreateRequest.getMemberCode(),
                memberProductInquiryCreateRequest.getInquiryTitle(),
                memberProductInquiryCreateRequest.getInquiryDetail(),
                memberProductInquiryCreateRequest.getInquiryWriteDate()
        );

        final InquiryContent newInquiry = memberProductInquiryRepository.save(newProductInquiry);

        return newInquiry.getInquiryCode();

    }
}
