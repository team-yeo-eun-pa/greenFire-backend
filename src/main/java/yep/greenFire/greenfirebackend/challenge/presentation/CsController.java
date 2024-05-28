package yep.greenFire.greenfirebackend.challenge.presentation;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import yep.greenFire.greenfirebackend.challenge.domain.entity.CsContent;
import yep.greenFire.greenfirebackend.challenge.dto.request.CsCreateRequest;
import yep.greenFire.greenfirebackend.challenge.dto.response.CsResponse;
import yep.greenFire.greenfirebackend.challenge.service.CsService;

import java.net.URI;

@RestController
@AllArgsConstructor
@RequestMapping("/member/cs") //경로 매핑 잘 하기
public class CsController {

    private CsService csService;

    @GetMapping("/list")
    @PreAuthorize("#memberId == authentication.principal.username")
    public ResponseEntity<CsResponse> getCsList(
            @RequestParam String memberId
            //사전 검사시 아이디를 활용하기 때문에, 아이디를 받겠다.
    ) {
        CsResponse csResponse = csService.getCsList(memberId);

        return ResponseEntity.ok(csResponse);
    }

    /* 5/27 해야할 것.
    *  1. 문의 상세보기 : 회원버전 -> 등록 후 등록된 목록 보기까지
    *  2. 문의 답변 달기 : 관리자 버전
    *  3. 문의 답변 수정 : 관리자 버전
    *  4. 문의/문의 답변 삭제 : 관리자 버전
    * => 등록된 답변이 없을 경우 상세 조회할 수 없다.. 등록부터 해야할 듯. */

//    @GetMapping("/list/detail")
//    public ResponseEntity<CsResponse> getCsDetail(
//            @RequestParam int csCode
//    )
//    {
//        CsResponse csResponse = csService.getCsDetail(csCode);
//        return ResponseEntity.ok(csResponse);
//
//    }

    @PostMapping("/list/regist")
    public ResponseEntity<Void> save (
           @RequestPart @Valid final CsCreateRequest csCreateRequest

    ) {

        final CsContent csCode = csService.save(csCreateRequest);
        return ResponseEntity.created(URI.create("/member/cs/list" + csCode)).build();


    }

    //홀리몰리 과콰몰리













}
