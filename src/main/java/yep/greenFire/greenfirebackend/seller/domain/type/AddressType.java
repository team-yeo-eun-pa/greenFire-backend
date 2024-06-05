package yep.greenFire.greenfirebackend.seller.domain.type;

public enum AddressType {
    R("r"),           // 도로명 주소
    J("j");           // 지번 주소

    private final String addressType;

    AddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getAddressType() {
        return addressType;
    }

    @Override
    public String toString() {
        return this.addressType;
    }
}
