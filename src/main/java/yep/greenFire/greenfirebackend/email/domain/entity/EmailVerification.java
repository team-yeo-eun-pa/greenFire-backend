package yep.greenFire.greenfirebackend.email.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_email_verification")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class EmailVerification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberCode;

    private String verificationCode;

    private LocalDateTime expirationTime;

    private boolean isVerified = false;

    private EmailVerification(Long memberCode, String verificationCode, LocalDateTime expirationTime) {
        this.memberCode = memberCode;
        this.verificationCode = verificationCode;
        this.expirationTime = expirationTime;
        this.isVerified = false;
    }

    public static EmailVerification of(Long memberCode, String verificationCode, LocalDateTime expirationTime) {
        return new EmailVerification(memberCode, verificationCode, expirationTime);
    }

    public void verify() {
        this.isVerified = true;
    }
}
