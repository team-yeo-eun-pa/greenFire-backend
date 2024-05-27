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












}
