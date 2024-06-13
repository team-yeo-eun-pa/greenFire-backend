package yep.greenFire.greenfirebackend.member.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import yep.greenFire.greenfirebackend.email.service.EmailVerificationService;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class VerificationController {

    private final EmailVerificationService emailVerificationService;

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
}
