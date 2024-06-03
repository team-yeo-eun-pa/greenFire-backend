package yep.greenFire.greenfirebackend.inquiry.product.seller.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import yep.greenFire.greenfirebackend.inquiry.entity.InquiryContent;

@Repository

public interface SellerProductInquiryRepository {


    Page<InquiryContent> findByProductCode(long productCode, Pageable page);

    InquiryContent save(InquiryContent newProductInquiryReply);

}
