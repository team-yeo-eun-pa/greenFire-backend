package yep.greenFire.greenfirebackend.product.domain.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum SellableStatus {

    Y("Y"),
    N("N"),
    D("D");

    private final String sellablestatus;

    SellableStatus(String sellablestatus) { this.sellablestatus = sellablestatus; }

    @JsonValue
    public String getSellablestatus() { return sellablestatus; }


}
