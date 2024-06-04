package yep.greenFire.greenfirebackend.seller.dto.response;

import lombok.Getter;
import yep.greenFire.greenfirebackend.store.domain.type.StoreStatus;

@Getter
public class StoreListResponse {

    private final Long sellerCode;
    private final Long memberCode;
    private final Long storeCode;
    private final String storeRepresentativeName;
    private final String storeName;
    private final StoreStatus storeStatus;

    public StoreListResponse(Long sellerCode, Long memberCode, Long storeCode, String storeRepresentativeName, String storeName, StoreStatus storeStatus) {
        this.sellerCode = sellerCode;
        this.memberCode = memberCode;
        this.storeCode = storeCode;
        this.storeRepresentativeName = storeRepresentativeName;
        this.storeName = storeName;
        this.storeStatus = storeStatus;
    }

}
