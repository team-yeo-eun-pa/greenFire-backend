package yep.greenFire.greenfirebackend.delivery.domain.type;

import com.fasterxml.jackson.annotation.JsonValue;
import yep.greenFire.greenfirebackend.common.exception.NotFoundException;
import yep.greenFire.greenfirebackend.common.exception.type.ExceptionCode;

public enum AddressType {
    R("r"),           // 도로명 주소
    J("j");           // 지번 주소

    private final String addressType;

    AddressType(String addressType) {
        this.addressType = addressType;
    }

    @Override
    public String toString() {
        return this.addressType;
    }

    @JsonValue
    public String getAddressType() {
        return addressType;
    }

    public static AddressType fromValue(String value) {
        for (AddressType addressType : AddressType.values()) {
            if (addressType.getAddressType().equals(value)) {
                return addressType;
            }
        }
        throw new NotFoundException(ExceptionCode.NOT_FOUND_VALID_DELIVERY);
    }
}
