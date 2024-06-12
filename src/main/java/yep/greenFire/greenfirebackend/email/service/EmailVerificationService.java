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
        emailService.sendEmail(email, "초록불 회원가입 인증번호", "인증번호: " + verificationCode);
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
            // 완료된 인증번호 테스트
            log.info("Current time: {}", now);
            log.info("Expiration time: {}", expirationTime);
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

    private String generateVerificationCode() {
        return UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }
}
