package yep.greenFire.greenfirebackend.user.seller.domain.type;

public enum CouponStatus {

    ACTIVE("active"),         // 활성
    INACTIVE("inactive"),     // 비활성
    DELETED("deleted");       // 삭제

    private final String couponStatus;

    CouponStatus(String status) {
        this.couponStatus = status;
    }

    public String getCouponStatus() {
        return couponStatus;
    }

    @Override
    public String toString() {
        return this.couponStatus;
    }
}
