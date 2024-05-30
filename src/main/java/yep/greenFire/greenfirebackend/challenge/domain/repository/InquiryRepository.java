package yep.greenFire.greenfirebackend.challenge.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yep.greenFire.greenfirebackend.challenge.domain.entity.InquiryContent;
import yep.greenFire.greenfirebackend.challenge.dto.response.AdminInquiryResponse;

import java.util.List;
import java.util.Optional;

@Repository
public interface InquiryRepository extends JpaRepository<InquiryContent, Integer> {
   Page<InquiryContent> findByMemberCode(Long memberCode, Pageable pageable);




    Page<AdminInquiryResponse> findByInquiryCode(Pageable page, int inquiryCode);





}
