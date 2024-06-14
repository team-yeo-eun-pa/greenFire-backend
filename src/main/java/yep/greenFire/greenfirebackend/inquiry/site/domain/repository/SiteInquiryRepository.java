package yep.greenFire.greenfirebackend.inquiry.site.domain.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yep.greenFire.greenfirebackend.inquiry.entity.InquiryContent;
import yep.greenFire.greenfirebackend.inquiry.site.dto.response.InquiryResponse;

@Repository

public interface SiteInquiryRepository extends JpaRepository<InquiryContent, Integer> {
    Page<InquiryContent> findByMemberCode(Long memberCode, Pageable pageable);


}