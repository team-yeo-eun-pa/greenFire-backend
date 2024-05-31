package yep.greenFire.greenfirebackend.admin.report.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yep.greenFire.greenfirebackend.user.member.service.MemberService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class SuspensionOverController {

    private final MemberService memberService;


    @PostMapping("/{memberCode}/suspendEnd")
    public ResponseEntity<String> suspendOver(@PathVariable Long memberCode) {
        memberService.suspensionEnd(memberCode);
        return ResponseEntity.ok("정지가 해제되었습니다");
    }


}
