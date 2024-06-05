package yep.greenFire.greenfirebackend.product.domain.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum SellableStatus {

    Y("sellable"),
    N("blocked");

    private final String value;

    SellableStatus(String value) { this.value = value; }

    @JsonValue
    public String getValue() { return value; }
}
