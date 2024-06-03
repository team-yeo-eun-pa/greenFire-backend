package yep.greenFire.greenfirebackend.inquiry.member.service;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestPart;
import yep.greenFire.greenfirebackend.inquiry.entity.InquiryContent;
import yep.greenFire.greenfirebackend.inquiry.member.domain.repository.MemberInquiryRepository;
import yep.greenFire.greenfirebackend.inquiry.member.dto.request.InquiryCreateRequest;
import yep.greenFire.greenfirebackend.challenge.dto.response.inquiry.InquiryResponse;



@Service
@Transactional
@RequiredArgsConstructor
public class MemberInquiryService {
    private final MemberInquiryRepository inquiryRepository;


    private Pageable getPageable(final Integer page) {
        return PageRequest.of(page - 1, 10, Sort.by("inquiryCode").descending());
    }

    @Transactional(readOnly = true)
    public Page<InquiryResponse> getInquiryContent(Long memberCode, Integer page) {
//
        Page<InquiryContent> inquiryContents = inquiryRepository.findByMemberCode(memberCode, getPageable(page));
        //페이징된 문의컨텐츠를 멤버코드와, 페이지 정보를 통해 조회하겠다.
        return inquiryContents.map(InquiryResponse::from);
        //문의컨텐츠의 문의 리스폰스타입을 매핑해서 반환하겠다

    }

    public int save(
            @RequestPart @Valid final InquiryCreateRequest inquiryCreateRequest

    ) {

        final InquiryContent newInquiryContent = InquiryContent.of(
                inquiryCreateRequest.getInquiryCode(),
                inquiryCreateRequest.getMemberCode(),
                inquiryCreateRequest.getInquiryWriteDate(),
                inquiryCreateRequest.getInquiryTitle(),
                inquiryCreateRequest.getInquiryDetail(),
                inquiryCreateRequest.getInquiryStatus()

        );

        final InquiryContent newContent = inquiryRepository.save(newInquiryContent);


       return newContent.getInquiryCode();
    }







}


