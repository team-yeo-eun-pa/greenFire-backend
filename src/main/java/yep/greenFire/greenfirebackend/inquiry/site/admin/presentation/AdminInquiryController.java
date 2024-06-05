package yep.greenFire.greenfirebackend.inquiry.site.admin.presentation;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yep.greenFire.greenfirebackend.inquiry.site.admin.dto.request.AdminInquiryCreateRequest;
import yep.greenFire.greenfirebackend.inquiry.site.admin.dto.response.AdminInquiryResponse;
import yep.greenFire.greenfirebackend.common.paging.Pagination;
import yep.greenFire.greenfirebackend.common.paging.PagingButtonInfo;
import yep.greenFire.greenfirebackend.common.paging.PagingResponse;
import yep.greenFire.greenfirebackend.inquiry.site.admin.service.AdminInquiryService;
import yep.greenFire.greenfirebackend.inquiry.entity.InquiryContent;

import java.net.URI;

@RestController
@RequestMapping("/inquiry/admin")
public class AdminInquiryController {

    //  private AdminInquiryService admininquiryService;

    //관리자 - 등록된 문의 조회

//    @GetMapping("/list")
//    //@PreAuthorize("#memberRole == authentication.principal.admin")
//    public ResponseEntity<PagingResponse> getAdminInquiryList(
//            //반환 받을 타입에 대한 일치가 중요하다
//
//            @RequestParam(defaultValue = "1") final Integer page,
//            @RequestParam int inquiryCode
//
//
//    ) {
    //final Page<AdminInquiryResponse> adminInquiryResponse = admininquiryService.getAdminInquiryList(page, inquiryCode);
    //페이지 기능을 적용한 리스폰스 타입에 대한 코드

    // final PagingButtonInfo pagingButtonInfo = Pagination.getPagingButtonInfo(adminInquiryResponse);
    //필요한 페이지의, 필요한 계산을 하겠다. (총 몇페이지이고, 몇 페이지를 보여줘야 할 건지)

    //  final PagingResponse pagingResponse = PagingResponse.of(adminInquiryResponse.getContent(), pagingButtonInfo);
    //최종적으로 계산되어진, 리스폰스타입의 페이지에 대한 코드


    // return ResponseEntity.ok(pagingResponse);


    //문의 답변 등록
//    @GetMapping("/regist")
//    public ResponseEntity<Void> save (
//            @RequestPart @Valid final AdminInquiryCreateRequest adminInquiryCreateRequest
//    ) {
//        final InquiryContent inquiryCode = admininquiryService.save(adminInquiryCreateRequest);
//        return ResponseEntity.created(URI.create("/admin/list" + inquiryCode)).build();
//    }
//
//
//    //문의 삭제
//
//    @GetMapping("/remove")
//    public ResponseEntity<Void> remove (@RequestParam final int inquiryCode) {
//        admininquiryService.remove(inquiryCode);
//
//        return ResponseEntity.noContent().build();
//    }
//}
}