package yep.greenFire.greenfirebackend.email.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import yep.greenFire.greenfirebackend.email.domain.entity.EmailVerification;
import yep.greenFire.greenfirebackend.email.domain.repository.EmailVerificationRepository;
import yep.greenFire.greenfirebackend.member.domain.entity.Member;
import yep.greenFire.greenfirebackend.member.domain.repository.MemberRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailVerificationService {

    private final EmailVerificationRepository emailVerificationRepository;
    private final MemberRepository memberRepository;
    private final EmailService emailService;

    public void generateAndSendVerificationCode(Long memberCode, String email) {
        String verificationCode = generateVerificationCode();
        LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(10); // 10분 후 만료

        EmailVerification emailVerification = EmailVerification.of(memberCode, verificationCode, expirationTime);
        emailVerificationRepository.save(emailVerification);

        // 인증 링크 생성
        String verificationLink = "http://localhost:8001/members/verify-email?memberCode=" + memberCode + "&verificationCode=" + verificationCode;

        // 이메일 내용 생성
        String emailContent = "<html>" +
                "<body>" +
                "<h3>초록불을 찾아주셔서 감사합니다:) <br/>본인인증을 완료하려면 아래 링크를 클릭해주세요.</h3>" +
                "<p><a href=\"" + verificationLink + "\" style=\"display: inline-block; padding: 10px 20px; font-size: 16px; color: white; background-color: #28a745; text-align: center; text-decoration: none; border-radius: 5px;\">초록불 시작하기</a></p>" +
                "<style>" +
                "a { text-decoration: none; }" +
                "p { font-family: Arial, sans-serif; }" +
                "</style>" +
                "</body>" +
                "</html>";

        emailService.sendEmail(email, "초록불 본인인증", emailContent);
    }

    public String verifyEmail(Long memberCode, String verificationCode) {
        Optional<EmailVerification> emailVerificationOpt = emailVerificationRepository.findByMemberCodeAndVerificationCode(memberCode, verificationCode);

        if (emailVerificationOpt.isPresent()) {
            EmailVerification emailVerification = emailVerificationOpt.get();
            if (emailVerification.isVerified()) {
                return "already_verified";
            }
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime expirationTime = emailVerification.getExpirationTime();
            if (expirationTime.isBefore(now)) {
                return "expired";
            }
            emailVerification.verify();
            emailVerificationRepository.save(emailVerification);

            Optional<Member> memberOpt = memberRepository.findById(memberCode);
            if (memberOpt.isPresent()) {
                Member member = memberOpt.get();
                member.activate();
                memberRepository.save(member);
                return "verified";
            }
        }
        return "invalid";
    }

    public void generateEmailAndSendPasswordResetCode(Long memberCode, String email) {
        String verificationCode = generateVerificationCode();
        LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(10); // 10분 후 만료

        EmailVerification emailVerification = EmailVerification.of(memberCode, verificationCode, expirationTime);
        emailVerificationRepository.save(emailVerification);

        // 인증 링크 생성
        String verificationLink = "http://localhost:8001/members/verify-password-reset?memberCode=" + memberCode + "&verificationCode=" + verificationCode;
        // 이메일 내용 생성
        String emailContent = "<html>" +
                "<body>" +
                "<h3>비밀번호 재설정을 위한 인증 링크입니다.</h3>" +
                "<p><a href=\"" + verificationLink + "\" style=\"display: inline-block; padding: 10px 20px; font-size: 16px; color: white; background-color: #28a745; text-align: center; text-decoration: none; border-radius: 5px;\">비밀번호 재설정</a></p>" +
                "<style>" +
                "a { text-decoration: none; }" +
                "p { font-family: Arial, sans-serif; }" +
                "</style>" +
                "</body>" +
                "</html>";

        emailService.sendEmail(email, "비밀번호 재설정 인증", emailContent);
    }

    public String verifyPasswordReset(Long memberCode, String verificationCode) {
        Optional<EmailVerification> emailVerificationOpt = emailVerificationRepository.findByMemberCodeAndVerificationCode(memberCode, verificationCode);

        if (emailVerificationOpt.isPresent()) {
            EmailVerification emailVerification = emailVerificationOpt.get();
            if (emailVerification.isVerified()) {
                return "already_verified";
            }
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime expirationTime = emailVerification.getExpirationTime();
            if (expirationTime.isBefore(now)) {
                return "expired";
            }
            emailVerification.verify();
            emailVerificationRepository.save(emailVerification);

            Optional<Member> memberOpt = memberRepository.findById(memberCode);
            if (memberOpt.isPresent()) {
                return "verified";
            }
        }
        return "invalid";
    }

    private String generateVerificationCode() {
        return UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }
}
