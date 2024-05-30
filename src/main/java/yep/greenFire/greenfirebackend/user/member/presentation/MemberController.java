package yep.greenFire.greenfirebackend.user.member.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import yep.greenFire.greenfirebackend.user.member.dto.request.MemberSignupRequest;
import yep.greenFire.greenfirebackend.user.member.dto.response.ProfileResponse;
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

    // 인증 테스트를 위한 메서드
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("test 응답 완료");
    }

    // 프로필 조회
    @GetMapping("/{memberId}")
    @PreAuthorize("#memberId == authentication.principal.username")
    public ResponseEntity<ProfileResponse> getProfile(@PathVariable String memberId) {

        ProfileResponse profileResponse = memberService.getProfile(memberId);

        return ResponseEntity.ok(profileResponse);
    }

}