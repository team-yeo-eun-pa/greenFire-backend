package yep.greenFire.greenfirebackend.inquiry.product.member.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import yep.greenFire.greenfirebackend.inquiry.entity.InquiryContent;


@Repository
public interface MemberProductInquiryRepository {

    Page<InquiryContent> findByProductCode(long productCode, Pageable pageable);

}
