//package yep.greenFire.greenfirebackend.email.service;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//import yep.greenFire.greenfirebackend.email.domain.repository.EmailVerificationRepository;
//
//import java.time.LocalDateTime;
//
//@Service
//@RequiredArgsConstructor
//public class EmailVerificationCleanupService {
//
//    private EmailVerificationRepository emailVerificationRepository;
//
//    @Scheduled(cron = "0 0 0 * * ?") // 매일 자정에 실행
//    public void cleanUpExpiredVerificationCodes() {
//        LocalDateTime now = LocalDateTime.now();
//        emailVerificationRepository.deleteByExpirationTimeBefore(now);
//    }
//
//}
