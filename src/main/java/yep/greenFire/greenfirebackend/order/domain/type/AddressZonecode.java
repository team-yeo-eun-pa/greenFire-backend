package yep.greenFire.greenfirebackend.order.domain.type;

public enum AddressZonecode {
    R("r"),           // 도로명 주소
    J("j");           // 지번 주소

    private final String addressZonecode;

    AddressZonecode(String addressZonecode) {
        this.addressZonecode = addressZonecode;
    }

    public String getAddressZonecode() {
        return addressZonecode;
    }

    @Override
    public String toString() {
        return this.addressZonecode;
    }
}
