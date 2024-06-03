package yep.greenFire.greenfirebackend.inquiry.site.admin.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yep.greenFire.greenfirebackend.inquiry.site.admin.dto.response.AdminInquiryResponse;
import yep.greenFire.greenfirebackend.inquiry.site.admin.dto.request.AdminInquiryCreateRequest;
import yep.greenFire.greenfirebackend.inquiry.site.admin.domain.repository.AdminInquiryRepository;
import yep.greenFire.greenfirebackend.inquiry.entity.InquiryContent;

@Service
public class AdminInquiryService {

    private AdminInquiryRepository adminInquiryRepository;

    private Pageable getPageable(final Integer page) {
        return PageRequest.of(page - 1, 10, Sort.by("inquiryCode").descending());
    }


    @Transactional(readOnly = true)

    public Page<AdminInquiryResponse> getAdminInquiryList(Integer page, int inquiryCode) {
        Page<AdminInquiryResponse> adminInquiryResponses = adminInquiryRepository.findByInquiryCode(getPageable(page), inquiryCode);
        return adminInquiryResponses.map(InquiryContent::from);

    }

    public InquiryContent save(AdminInquiryCreateRequest adminInquiryCreateRequest) {
//        InquiryContent newReply = (InquiryContent) inquiryRepository.findByInquiryCode(admincsCreateRequest.getInquiryCode());
//        //문의 답변을 뉴리플라이에 담아서 반환하는 동작

        final InquiryContent newAdminList = InquiryContent.of2(
                adminInquiryCreateRequest.getInquiryCode(),
                adminInquiryCreateRequest.getInquiryWriteDate(),
                adminInquiryCreateRequest.getInquiryTitle(),
                adminInquiryCreateRequest.getInquiryDetail()
        );
        //이런 내용을 토대로 뉴리플라이를 구성한다

        final InquiryContent newInquiryReply = adminInquiryRepository.save(newAdminList);
        //새로운 내용을 adminCs 라는 변수에 담아 전달하겠다.


        return newInquiryReply;




    }

    public void remove(int inquiryCode) {

        adminInquiryRepository.deleteById(inquiryCode);
        //특정 문의 코드의 글을 삭제하겠다.

    }
}
