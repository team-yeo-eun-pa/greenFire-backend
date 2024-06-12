package yep.greenFire.greenfirebackend.payment.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import yep.greenFire.greenfirebackend.payment.domain.type.CancelStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_payment_cancel")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EntityListeners(AuditingEntityListener.class)
public class PaymentCancel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentCancelCode;
    private Long paymentCode;

    private String cancelReason;
    private Long cancelAmount;
    private LocalDateTime canceledDate;
    private String transactionKey;

    @Enumerated(value = EnumType.STRING)
    private CancelStatus cancelStatus;

}
