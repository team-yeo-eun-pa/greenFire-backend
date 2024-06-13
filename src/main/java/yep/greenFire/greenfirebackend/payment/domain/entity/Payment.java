package yep.greenFire.greenfirebackend.payment.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import yep.greenFire.greenfirebackend.payment.domain.type.PaymentStatus;
import yep.greenFire.greenfirebackend.payment.domain.type.PaymentWay;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_payment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Payment{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentCode;
    private Long orderCode;

    private String orderId;
    private String paymentKey;

    @Enumerated(value = EnumType.STRING)
    private PaymentWay paymentWay;
    private Long paymentAmount;

    private Long balanceAmount;
    private LocalDateTime requestedDate;
    private LocalDateTime approvedDate;
    private String lastTransactionKey;

    @Enumerated(value = EnumType.STRING)
    private PaymentStatus paymentStatus;

    private Payment(String orderId, Long paymentAmount) {
        this.orderId = orderId;
        this.paymentAmount = paymentAmount;
    }

    public static Payment of(String orderId, Long paymentAmount) {
        return new Payment(orderId, paymentAmount);
    }

    public void modifyPayment(String paymentKey, PaymentWay paymentWay,
                              Long balanceAmount, LocalDateTime requestedDate,
                              LocalDateTime approvedDate, String laseTransactionKey,
                              PaymentStatus paymentStatus) {
        this.paymentKey = paymentKey;
        this.paymentWay = paymentWay;
        this.balanceAmount = balanceAmount;
        this.requestedDate = requestedDate;
        this.approvedDate = approvedDate;
        this.lastTransactionKey = laseTransactionKey;
        this.paymentStatus = paymentStatus;
    }
}
