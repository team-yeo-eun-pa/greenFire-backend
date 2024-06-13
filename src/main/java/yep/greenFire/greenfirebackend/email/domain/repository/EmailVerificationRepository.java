//package yep.greenFire.greenfirebackend.email.domain.repository;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import yep.greenFire.greenfirebackend.email.domain.entity.EmailVerification;
//
//import java.time.LocalDateTime;
//import java.util.Optional;
//
//public interface EmailVerificationRepository extends JpaRepository<EmailVerification, Long> {
//
//    Optional<EmailVerification> findByMemberCodeAndVerificationCode(Long memberCode, String verificationCode);
//    void deleteByExpirationTimeBefore(LocalDateTime now);
//
//}
