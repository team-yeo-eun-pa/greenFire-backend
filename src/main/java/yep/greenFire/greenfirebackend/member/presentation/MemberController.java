package yep.greenFire.greenfirebackend.member.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import yep.greenFire.greenfirebackend.auth.type.CustomUser;
import yep.greenFire.greenfirebackend.member.dto.request.MemberSignupRequest;
import yep.greenFire.greenfirebackend.member.dto.request.ProfileUpdateRequest;
import yep.greenFire.greenfirebackend.member.service.MemberService;
import yep.greenFire.greenfirebackend.member.dto.response.ProfileResponse;

import java.net.URI;

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
    @GetMapping("/mypage/{memberId}")
    //@PreAuthorize("#memberId == authentication.principal.username")
    public ResponseEntity<ProfileResponse> getProfile(@PathVariable String memberId) {

        ProfileResponse profileResponse = memberService.getProfile(memberId);

        return ResponseEntity.ok(profileResponse);
    }

    // 프로필 수정
    @PutMapping("/mypage/{memberId}")
    public ResponseEntity<Void> modifyProfile(
            @PathVariable final String memberId,
            @RequestBody @Valid final ProfileUpdateRequest profileRequest,
            @AuthenticationPrincipal final CustomUser customUser
            ) {

        memberService.modifyProfile(memberId, profileRequest, customUser.getMemberCode());

        return ResponseEntity.noContent().build();
    }

    // 프로필 사진 등록

    // 프로필 사진 삭제


    // 로그아웃 시 DB 토큰 무효화
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@AuthenticationPrincipal UserDetails userDetails) {

        memberService.updateRefreshToken(userDetails.getUsername(), null);

        return ResponseEntity.ok().build();
    }

}