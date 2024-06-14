package yep.greenFire.greenfirebackend.inquiry.site.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import yep.greenFire.greenfirebackend.auth.type.CustomUser;
import yep.greenFire.greenfirebackend.inquiry.entity.InquiryContent;
import yep.greenFire.greenfirebackend.inquiry.site.domain.repository.SiteInquiryRepository;
import yep.greenFire.greenfirebackend.inquiry.site.dto.request.InquiryCreateRequest;
import yep.greenFire.greenfirebackend.inquiry.site.dto.response.InquiryResponse;

import java.util.Date;

@Service
@Transactional
@RequiredArgsConstructor
public class SiteInquiryService {

    private final SiteInquiryRepository siteInquiryRepository;
    private Pageable getPageable(final Integer page) {
        return PageRequest.of(page - 1, 10, Sort.by("inquiryCode").descending());
    }


    public Page<InquiryResponse> getInquiryContent(Long memberCode, Integer page) {

        Page<InquiryContent> inquiryContents = siteInquiryRepository.findByMemberCode(memberCode, getPageable(page));
        //페이징된 문의컨텐츠를 멤버코드와, 페이지 정보를 통해 조회하겠다.
        return inquiryContents.map(InquiryResponse::from);
        //문의컨텐츠의 문의 리스폰스타입을 매핑해서 반환하겠다

    }



        public int save(
            InquiryCreateRequest inquiryCreateRequest,
            Long memberCode
    ) {

        final InquiryContent newInquiryContent = InquiryContent.of(
                memberCode,
                inquiryCreateRequest.getInquiryTitle(),
                inquiryCreateRequest.getInquiryDetail()



        );

        final InquiryContent newContent = siteInquiryRepository.save(newInquiryContent);

        return newContent.getInquiryCode();
    }

    @Transactional(readOnly = true)
    public InquiryResponse findByInquiryCode(Long inquiryCode) {
        InquiryResponse inquiryDetail = siteInquiryRepository.findByInquiryCode(inquiryCode);

        return inquiryDetail;
    }


    //문의 답변 등록

//    public int Replysave(
//            @RequestBody @Valid ReplyInquiryCreateRequest replyInquiryCreateRequest,
//            @AuthenticationPrincipal CustomUser customUser) {
//
//        final InquiryContent newInquiryReply= InquiryContent.of2(
//                replyInquiryCreateRequest.getMemberCode(),
//                replyInquiryCreateRequest.getInquiryCode(),
//                replyInquiryCreateRequest.getInquiryTitle(),
//                replyInquiryCreateRequest.getInquiryDetail(),
//                replyInquiryCreateRequest.getInquiryWriteDate()
//
//        );
//
//        final InquiryContent newContent = siteInquiryRepository.save(newInquiryReply);
//
//        return newContent.getInquiryCode();
//    }
//
    //문의 삭제
//    public void remove(int inquiryCode) {
//
//        siteInquiryRepository.deleteById(inquiryCode);
//        //특정 문의 코드의 글을 삭제하겠다.
//
//    }
}
