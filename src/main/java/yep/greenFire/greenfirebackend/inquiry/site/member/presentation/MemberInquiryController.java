package yep.greenFire.greenfirebackend.inquiry.site.member.presentation;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yep.greenFire.greenfirebackend.inquiry.site.member.dto.request.InquiryCreateRequest;
import yep.greenFire.greenfirebackend.inquiry.site.member.dto.response.MemberInquiryResponse;
import yep.greenFire.greenfirebackend.inquiry.site.member.service.MemberInquiryService;
import yep.greenFire.greenfirebackend.common.paging.Pagination;
import yep.greenFire.greenfirebackend.common.paging.PagingButtonInfo;
import yep.greenFire.greenfirebackend.common.paging.PagingResponse;

import java.net.URI;

@RestController
@AllArgsConstructor
@RequestMapping("/inquiry/member")
public class MemberInquiryController {
    private MemberInquiryService memberinquiryService;



    @GetMapping("/list")
   // @PreAuthorize("#memberId == authentication.principal.username")
    public ResponseEntity<PagingResponse> getInquiryContent(
            @RequestParam (defaultValue = "1") final Integer page
            //userId
            //@AuthenticationPrincipal UserDetails userDetails
            //사전 검사시 아이디를 활용하기 때문에, 아이디를 받겠다.
    ) {
       final Page<MemberInquiryResponse> inquiryResponse = memberinquiryService.getInquiryContent(1L, page);

        final PagingButtonInfo pagingButtonInfo = Pagination.getPagingButtonInfo(inquiryResponse);
        final PagingResponse pagingResponse = PagingResponse.of(inquiryResponse.getContent(), pagingButtonInfo);

        return ResponseEntity.ok(pagingResponse);
    }

    @PostMapping("/regist")
    public ResponseEntity<MemberInquiryResponse> save (
            @RequestPart @Valid final InquiryCreateRequest inquiryCreateRequest

    ) {

        final int inquiryCode = memberinquiryService.save(inquiryCreateRequest);
        return ResponseEntity.created(URI.create("/list" +inquiryCode)).build();


    }













}
