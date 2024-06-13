//package yep.greenFire.greenfirebackend.auth.presentaition;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import yep.greenFire.greenfirebackend.email.dto.request.VerificationRequest;
//import yep.greenFire.greenfirebackend.email.service.EmailVerificationService;
//
//@RestController
//@RequestMapping("/api/auth")
//@RequiredArgsConstructor
//public class AuthController {
//
//    private final EmailVerificationService emailVerificationService;
//
//    @PostMapping("/verify-email")
//    public ResponseEntity<?> verifyEmail(@RequestBody VerificationRequest verificationRequest) {
//        String verificationResult = emailVerificationService.verifyEmail(verificationRequest.getMemberCode(), verificationRequest.getVerificationCode());
//
//        switch (verificationResult) {
//            case "verified":
//                return ResponseEntity.ok("회원가입이 완료되었습니다.");
//            case "expired":
//                return ResponseEntity.badRequest().body("인증코드가 만료되었습니다.");
//            case "already_verified":
//                return ResponseEntity.badRequest().body("이미 인증이 완료된 코드입니다.");
//            default:
//                return ResponseEntity.badRequest().body("인증코드가 다르거나 만료되었습니다.");
//        }
//    }
//}