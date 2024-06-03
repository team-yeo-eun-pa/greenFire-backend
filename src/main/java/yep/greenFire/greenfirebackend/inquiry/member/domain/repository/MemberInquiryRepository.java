package yep.greenFire.greenfirebackend.inquiry.member.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yep.greenFire.greenfirebackend.inquiry.entity.InquiryContent;
import yep.greenFire.greenfirebackend.challenge.dto.response.inquiry.AdminInquiryResponse;

@Repository
public interface MemberInquiryRepository extends JpaRepository<InquiryContent, Integer> {
   Page<InquiryContent> findByMemberCode(Long memberCode, Pageable pageable);








}
