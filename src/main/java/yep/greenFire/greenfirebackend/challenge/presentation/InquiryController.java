package yep.greenFire.greenfirebackend.challenge.presentation;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import yep.greenFire.greenfirebackend.challenge.domain.entity.InquiryContent;
import yep.greenFire.greenfirebackend.challenge.dto.request.AdminCsCreateRequest;
import yep.greenFire.greenfirebackend.challenge.dto.request.CsCreateRequest;
import yep.greenFire.greenfirebackend.challenge.dto.response.AdminInquiryResponse;
import yep.greenFire.greenfirebackend.challenge.dto.response.InquiryResponse;
import yep.greenFire.greenfirebackend.challenge.service.InquiryService;
import yep.greenFire.greenfirebackend.common.paging.Pagination;
import yep.greenFire.greenfirebackend.common.paging.PagingButtonInfo;
import yep.greenFire.greenfirebackend.common.paging.PagingResponse;

import java.net.URI;

@RestController
@AllArgsConstructor
@RequestMapping("/inquiry") //경로매핑 잘하기
public class InquiryController {
    private InquiryService inquiryService;

    @GetMapping("/me")
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

//    @PostMapping("/member/list/regist")
//    public ResponseEntity<Void> save (
//            @RequestPart @Valid final CsCreateRequest csCreateRequest
//
//    ) {
//
//        final InquiryContent inquiryCode = inquiryService.save(csCreateRequest);
//        return ResponseEntity.created(URI.create("/member/cs/list" +inquiryCode)).build();
//
//
//    }
//    /* 5/28 해야할 것.
//     *  1. 문의 상세보기 : 회원버전
//     *  2. 문의 답변 달기 : 관리자 버전
//     *  3. 문의 답변 수정 : 관리자 버전
//     *  4. 문의/문의 답변 삭제 : 관리자 버전
//     * => 등록된 답변이 없을 경우 상세 조회할 수 없다.. 등록부터 해야할 듯. */
//
//        @GetMapping("/member/list/detail")
//    public ResponseEntity<InquiryResponse> getCsDetail(
//            @RequestParam int inquiryCode
//    )
//    {
//        InquiryResponse inquiryResponse = inquiryService.getCsDetail(inquiryCode);
//        return ResponseEntity.ok(inquiryResponse);
//
//    }
//
//    /* 5/29 해야할 것
//    * 1. 관리자 : 등록된 문의 조회
//    * 2. 관리자 : 문의 답변 등록
//    * 3. 관리자 : 업데이트된 문의 목록 조회
//    * 관리자 모드에서의 문의 목록 조회는 버튼모양으로 상태값을 보여줘야 한다
//    **/
//
//
//    //관리자 - 등록된 문의 조회
//    @GetMapping("/admin/list")
//    @PreAuthorize("#memberRole == authentication.principal.admin")
//    public ResponseEntity<AdminInquiryResponse> getAdminCsList(
//            @RequestParam int inquiryCode
//
//    ) {
//        AdminInquiryResponse adminInquiryResponse = inquiryService.getAdminCsList(inquiryCode);
//
//        return ResponseEntity.ok(adminInquiryResponse);
//
//
//    }
//
//    //문의 답변 등록
//    @GetMapping("/admin/list/{inquiryCode}")
//    @PreAuthorize("#memberRole == authentication.principal.admin")
//    public ResponseEntity<Void> saveAdmin (
//            @RequestPart @Valid final AdminCsCreateRequest admincsCreateRequest
//    ) {
//        final InquiryContent inquiryCode = inquiryService.saveAdmin(admincsCreateRequest);
//        return ResponseEntity.created(URI.create("/admin/list" + inquiryCode)).build();
//    }



}
