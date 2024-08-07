package yep.greenFire.greenfirebackend.member.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import yep.greenFire.greenfirebackend.auth.type.CustomUser;
import yep.greenFire.greenfirebackend.email.service.EmailVerificationService;
import yep.greenFire.greenfirebackend.member.dto.request.*;
import yep.greenFire.greenfirebackend.member.service.MemberService;
import yep.greenFire.greenfirebackend.member.dto.response.ProfileResponse;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final EmailVerificationService emailVerificationService;

    // 회원 가입
    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody @Valid MemberSignupRequest memberRequest) {
        Long memberCode = memberService.signup(memberRequest);
        emailVerificationService.generateAndSendVerificationCode(memberCode, memberRequest.getMemberEmail());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // 아이디 중복 체크
    @PostMapping("/check-id")
    public ResponseEntity<Boolean> checkMemberId(@RequestBody @Valid CheckMemberIdRequest checkMemberIdRequest) {
        boolean isAvailable = memberService.checkMemberId(checkMemberIdRequest.getMemberId());
        return ResponseEntity.ok(isAvailable);
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

    // 로그아웃 시 DB 토큰 무효화
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@AuthenticationPrincipal UserDetails userDetails) {

        memberService.updateRefreshToken(userDetails.getUsername(), null);

        return ResponseEntity.ok().build();
    }

    // 아이디 찾기
    @PostMapping("/find-id")
    public ResponseEntity<String> findMemberId(@RequestBody @Valid FindMemberIdRequest findIdRequest) {

        String memberId = memberService.findMemberIdByEmail(findIdRequest.getMemberEmail());

        return ResponseEntity.ok(memberId);
    }

    // 비밀번호 찾기
    @PostMapping("/find-pwd")
    public ResponseEntity<Void> findMemberPwd(@RequestBody @Valid FindMemberPwdRequest findPwdRequest) {

        Long memberCode = memberService.findMemberCodeByEmail(findPwdRequest.getMemberEmail());

        emailVerificationService.generateEmailAndSendPasswordResetCode(memberCode, findPwdRequest.getMemberEmail());

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // 비밀번호 재설정
    @PutMapping("/reset-password")
    public ResponseEntity<Void> resetPassword(@RequestBody @Valid ResetPasswordRequest resetPasswordRequest) {

        boolean success = memberService.modifyMemberPassword(resetPasswordRequest);
        if (success) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}