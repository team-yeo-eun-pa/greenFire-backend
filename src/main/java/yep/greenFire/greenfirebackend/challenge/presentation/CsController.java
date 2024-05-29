package yep.greenFire.greenfirebackend.challenge.presentation;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import yep.greenFire.greenfirebackend.challenge.domain.entity.CsContent;
import yep.greenFire.greenfirebackend.challenge.dto.request.CsCreateRequest;
import yep.greenFire.greenfirebackend.challenge.dto.response.AdminCsResponse;
import yep.greenFire.greenfirebackend.challenge.dto.response.CsResponse;
import yep.greenFire.greenfirebackend.challenge.service.CsService;

import java.net.URI;

@RestController
@AllArgsConstructor
@RequestMapping("/cs") //경로매핑 잘하기
public class CsController {
    private CsService csService;

    @GetMapping("/member/list")
    @PreAuthorize("#memberId == authentication.principal.username")
    public ResponseEntity<CsResponse> getCsList(
            @RequestParam String memberId
            //사전 검사시 아이디를 활용하기 때문에, 아이디를 받겠다.
    ) {
        CsResponse csResponse = csService.getCsList(memberId);

        return ResponseEntity.ok(csResponse);
    }

    @PostMapping("/member/list/regist")
    public ResponseEntity<Void> save (
            @RequestPart @Valid final CsCreateRequest csCreateRequest

    ) {

        final CsContent csCode = csService.save(csCreateRequest);
        return ResponseEntity.created(URI.create("/member/cs/list" + csCode)).build();


    }
    /* 5/28 해야할 것.
     *  1. 문의 상세보기 : 회원버전
     *  2. 문의 답변 달기 : 관리자 버전
     *  3. 문의 답변 수정 : 관리자 버전
     *  4. 문의/문의 답변 삭제 : 관리자 버전
     * => 등록된 답변이 없을 경우 상세 조회할 수 없다.. 등록부터 해야할 듯. */

        @GetMapping("/member/list/detail")
    public ResponseEntity<CsResponse> getCsDetail(
            @RequestParam int csCode
    )
    {
        CsResponse csResponse = csService.getCsDetail(csCode);
        return ResponseEntity.ok(csResponse);

    }

    /* 5/29 해야할 것
    * 1. 관리자 : 등록된 문의 조회
    * 2. 관리자 : 문의 답변 등록
    * 3. 관리자 : 업데이트된 문의 목록 조회
    * 관리자 모드에서의 문의 목록 조회는 버튼모양으로 상태값을 보여줘야 한다
    **/


    @GetMapping("/admin/list")
    @PreAuthorize("#memberRole == authentication.principal.admin")
    public ResponseEntity<AdminCsResponse> getAdminCsList(
            @RequestParam int csCode

    ) {
        AdminCsResponse adminCsResponse = csService.getAdminCsList(csCode);

        return ResponseEntity.ok(adminCsResponse);
    }




}
