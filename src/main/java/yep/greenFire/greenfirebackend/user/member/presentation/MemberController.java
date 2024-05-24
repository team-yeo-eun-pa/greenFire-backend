package yep.greenFire.greenfirebackend.user.member.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yep.greenFire.greenfirebackend.user.member.dto.request.MemberSignupRequest;
import yep.greenFire.greenfirebackend.user.member.service.MemberService;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 회원 가입
    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody @Valid MemberSignupRequest memberRequest) {

        memberService.signup(memberRequest);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}