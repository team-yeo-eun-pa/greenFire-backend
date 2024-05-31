package yep.greenFire.greenfirebackend.challenge.service;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestPart;
import yep.greenFire.greenfirebackend.challenge.domain.entity.InquiryContent;
import yep.greenFire.greenfirebackend.challenge.domain.repository.InquiryRepository;
import yep.greenFire.greenfirebackend.challenge.dto.request.AdminInquiryCreateRequest;
import yep.greenFire.greenfirebackend.challenge.dto.request.InquiryCreateRequest;
import yep.greenFire.greenfirebackend.challenge.dto.response.inquiry.AdminInquiryResponse;
import yep.greenFire.greenfirebackend.challenge.dto.response.inquiry.InquiryResponse;



@Service
@Transactional
@RequiredArgsConstructor
public class InquiryService {
    private final InquiryRepository inquiryRepository;
    private final InquiryResponse inquiryResponse;

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



    @Transactional(readOnly = true)

    public Page<AdminInquiryResponse> getAdminInquiryList(Integer page, int inquiryCode) {
        Page<AdminInquiryResponse> adminInquiryResponses = inquiryRepository.findByInquiryCode(getPageable(page), inquiryCode);
        return adminInquiryResponses.map(InquiryContent::from);

    }


    public InquiryContent saveAdmin(AdminInquiryCreateRequest adminInquiryCreateRequest) {
//        InquiryContent newReply = (InquiryContent) inquiryRepository.findByInquiryCode(admincsCreateRequest.getInquiryCode());
//        //문의 답변을 뉴리플라이에 담아서 반환하는 동작

        final InquiryContent newAdminList = InquiryContent.of2(
                adminInquiryCreateRequest.getInquiryCode(),
                adminInquiryCreateRequest.getInquiryWriteDate(),
                adminInquiryCreateRequest.getInquiryTitle(),
                adminInquiryCreateRequest.getInquiryDetail()
        );
        //이런 내용을 토대로 뉴리플라이를 구성한다

        final InquiryContent adminInquiry = inquiryRepository.save(newAdminList);
        //새로운 내용을 adminCs 라는 변수에 담아 전달하겠다.


        return adminInquiry;
    }

    public void remove(int inquiryCode) {

        inquiryRepository.deleteById(inquiryCode);
        //특정 문의 코드의 글을 삭제하겠다.

    }
}


