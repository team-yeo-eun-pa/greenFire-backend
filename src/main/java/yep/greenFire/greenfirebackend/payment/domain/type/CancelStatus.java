package yep.greenFire.greenfirebackend.payment.domain.type;

public enum CancelStatus {

    DONE("DONE");

    private final String cancelStatus;

    CancelStatus(String cancelStatus) {
        this.cancelStatus = cancelStatus;
    }

    public String getCancelStatus() {
        return cancelStatus;
    }

    public static CancelStatus fromValue(String status) {
        for (CancelStatus cancelStatus : CancelStatus.values()) {
            if (cancelStatus.getCancelStatus().equals(status)) {
                return cancelStatus;
            }
        }
        throw new IllegalArgumentException("Unknown cancelStatus: " + status);
    }
}
