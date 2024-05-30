package yep.greenFire.greenfirebackend.challenge.presentation;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yep.greenFire.greenfirebackend.challenge.domain.entity.InquiryContent;
import yep.greenFire.greenfirebackend.challenge.dto.request.AdminInquiryCreateRequest;
import yep.greenFire.greenfirebackend.challenge.dto.request.InquiryCreateRequest;
import yep.greenFire.greenfirebackend.challenge.dto.response.AdminInquiryResponse;
import yep.greenFire.greenfirebackend.challenge.dto.response.InquiryResponse;
import yep.greenFire.greenfirebackend.challenge.service.InquiryService;
import yep.greenFire.greenfirebackend.common.paging.Pagination;
import yep.greenFire.greenfirebackend.common.paging.PagingButtonInfo;
import yep.greenFire.greenfirebackend.common.paging.PagingResponse;

import java.net.URI;

@RestController
@AllArgsConstructor
public class InquiryController {
    private InquiryService inquiryService;


    @GetMapping("/member/inquiry")
   // @PreAuthorize("#memberId == authentication.principal.username")
    public ResponseEntity<PagingResponse> getInquiryContent(
            @RequestParam (defaultValue = "1") final Integer page
            //userId
            //@AuthenticationPrincipal UserDetails userDetails
            //사전 검사시 아이디를 활용하기 때문에, 아이디를 받겠다.
    ) {
       final Page<InquiryResponse> inquiryResponse = inquiryService.getInquiryContent(1L, page);

        final PagingButtonInfo pagingButtonInfo = Pagination.getPagingButtonInfo(inquiryResponse);
        final PagingResponse pagingResponse = PagingResponse.of(inquiryResponse.getContent(), pagingButtonInfo);

        return ResponseEntity.ok(pagingResponse);
    }

    @PostMapping("/member/inquiry/regist")
    public ResponseEntity<InquiryResponse> save (
            @RequestPart @Valid final InquiryCreateRequest inquiryCreateRequest

    ) {

        final int inquiryCode = inquiryService.save(inquiryCreateRequest);
        return ResponseEntity.created(URI.create("/me" +inquiryCode)).build();


    }



    //관리자 - 등록된 문의 조회

    @GetMapping("/admin/inquiry")
    //@PreAuthorize("#memberRole == authentication.principal.admin")
    public ResponseEntity<PagingResponse> getAdminInquiryList(
            //반환 받을 타입에 대한 일치가 중요하다

            @RequestParam (defaultValue = "1") final Integer page,
            @RequestParam int inquiryCode


    ) {
        final Page<AdminInquiryResponse>  adminInquiryResponse = inquiryService.getAdminInquiryList(page, inquiryCode);
        //페이지 기능을 적용한 리스폰스 타입에 대한 코드

        final PagingButtonInfo pagingButtonInfo = Pagination.getPagingButtonInfo(adminInquiryResponse);
        //필요한 페이지의, 필요한 계산을 하겠다. (총 몇페이지이고, 몇 페이지를 보여줘야 할 건지)

        final PagingResponse pagingResponse = PagingResponse.of(adminInquiryResponse.getContent(), pagingButtonInfo);
        //최종적으로 계산되어진, 리스폰스타입의 페이지에 대한 코드


        return ResponseEntity.ok(pagingResponse);


    }

    //문의 답변 등록
    @GetMapping("/admin/inquiry/regist")
    public ResponseEntity<Void> saveAdmin (
            @RequestPart @Valid final AdminInquiryCreateRequest admincsCreateRequest
    ) {
        final InquiryContent inquiryCode = inquiryService.saveAdmin(admincsCreateRequest);
        return ResponseEntity.created(URI.create("/admin/list" + inquiryCode)).build();
    }



}
