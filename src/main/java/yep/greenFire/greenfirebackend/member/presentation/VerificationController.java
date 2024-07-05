package yep.greenFire.greenfirebackend.member.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import yep.greenFire.greenfirebackend.email.service.EmailVerificationService;
import yep.greenFire.greenfirebackend.member.service.MemberService;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class VerificationController {

    private final EmailVerificationService emailVerificationService;

    // 인증 결과 페이지로 리다이렉트
    @GetMapping("/verify-email")
    public String verifyEmail(@RequestParam Long memberCode, @RequestParam String verificationCode) {
        String result = emailVerificationService.verifyEmail(memberCode, verificationCode);

        switch (result) {
            case "verified":
                return "redirect:http://localhost:3000/verify-email/success";
            case "expired":
                return "redirect:http://localhost:3000/verify-email/expired";
            case "already_verified":
                return "redirect:http://localhost:3000/verify-email/verified";
            default:
                return "redirect:http://localhost:3000/verify-email/error";
        }
    }

    // 비밀번호 재설정 페이지로 리다이렉트
    @GetMapping("/verify-password-reset")
    public String verifyPasswordReset(@RequestParam Long memberCode, @RequestParam String verificationCode) {
        String result = emailVerificationService.verifyPasswordReset(memberCode, verificationCode);

        switch (result) {
            case "verified":
                return "redirect:http://localhost:3000/reset-password?memberCode=" + memberCode + "&verificationCode=" + verificationCode;
            case "expired":
                return "redirect:http://localhost:3000/verify-password-reset/expired";
            case "already_verified":
                return "redirect:http://localhost:3000/verify-password-reset/verified";
            default:
                return "redirect:http://localhost:3000/verify-password-reset/error";
        }
    }

}
