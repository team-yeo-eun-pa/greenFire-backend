package yep.greenFire.greenfirebackend.payment.domain.type;

public enum PaymentWay {
    CARD("카드"),
    VIRTUAL_ACCOUNT("가상계좌"),
    EASY_PAY("간편결제"),
    MOBILE_PHONE("휴대폰");

    private final String paymentWay;

    PaymentWay(String paymentWay) {
        this.paymentWay = paymentWay;
    }

    public String getPaymentWay() {
        return paymentWay;
    }

    public static PaymentWay fromValue(String value) {
        for (PaymentWay paymentWay : PaymentWay.values()) {
            if (paymentWay.getPaymentWay().equals(value)) {
                return paymentWay;
            }
        }
        throw new IllegalArgumentException("Unknown paymentWay: " + value);
    }
}