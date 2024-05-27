package yep.greenFire.greenfirebackend.challenge.presentation;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import yep.greenFire.greenfirebackend.challenge.dto.response.CsResponse;
import yep.greenFire.greenfirebackend.challenge.service.CsService;

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
    *  4. 문의/문의 답변 삭제 : 관리자 버전*/

    @GetMapping("/list/detail")
    public ResponseEntity<CsResponse> getCsDetail(
            @RequestParam int csCode
    )
    {
        CsResponse csResponse = csService.getCsDetail(csCode);
        return ResponseEntity.ok(csResponse);

    }












}
