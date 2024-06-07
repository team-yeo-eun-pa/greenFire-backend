package yep.greenFire.greenfirebackend.payment.domain.type;

public enum PaymentStatus {
    READY("READY"),
    IN_PROGRESS("IN_PROGRESS"),
    DONE("DONE"),
    CANCELED("CANCELED"),
    PARTIAL_CANCELED("PARTIAL_CANCELED"),
    ABORTED("ABORTED"),
    EXPIRED("EXPIRED");

    private final String paymentStatus;

    PaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public static PaymentStatus fromValue(String status) {
        for (PaymentStatus paymentStatus : PaymentStatus.values()) {
            if (paymentStatus.getPaymentStatus().equals(status)) {
                return paymentStatus;
            }
        }
        throw new IllegalArgumentException("Unknown paymentStatus: " + status);
    }
}
