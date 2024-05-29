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
import yep.greenFire.greenfirebackend.challenge.dto.request.InquiryCreateRequest;
import yep.greenFire.greenfirebackend.challenge.dto.response.InquiryResponse;

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
        return inquiryContents.map(InquiryResponse::from);

    }

    public int save(
            @RequestPart @Valid final InquiryCreateRequest inquiryCreateRequest

    ) {

       // ResponseEntity<InquiryResponse> inquiryResponseEntity = new ResponseEntity<InquiryResponse>();

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
//
//    public InquiryResponse getCsDetail(int inquiryCode) {
//        final InquiryContent csList = inquiryRepository.findAllInquiryContents(inquiryCode);
////                .orElseThrow(()-> new NotFoundException(ExceptionCode.NOT_FOUND_CS_CODE));
//        //익셉션 코드 문제 해결하기
//
//      return InquiryAllResponse.from(csList);
//
//    }
//
//
//    public AdminInquiryResponse getAdminCsList(int inquiryCode) {
//        InquiryContent adminInquiryContent = (InquiryContent) inquiryRepository.findByInquiryCode(inquiryCode);
//
//        return AdminInquiryResponse.from(adminInquiryContent);
//    }
//
//    public InquiryContent saveAdmin(AdminCsCreateRequest admincsCreateRequest) {
//        InquiryContent newReply = (InquiryContent) inquiryRepository.findByInquiryCode(admincsCreateRequest.getInquiryCode());
//
//        final InquiryContent newAdminList = InquiryContent.of(
//                admincsCreateRequest.getInquiryCode(),
//                admincsCreateRequest.getInquiryWriteDate(),
//                admincsCreateRequest.getInquiryTitle(),
//                admincsCreateRequest.getInquiryDetail()
//        );
//
//        final InquiryContent adminCs = inquiryRepository.save(newAdminList);
//
//
//        return newReply;
//    }

}


