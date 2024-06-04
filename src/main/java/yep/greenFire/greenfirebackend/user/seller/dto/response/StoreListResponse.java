package yep.greenFire.greenfirebackend.user.seller.dto.response;

import lombok.Getter;
import yep.greenFire.greenfirebackend.store.domain.type.StoreStatus;

@Getter
public class StoreListResponse {

    private final Long sellerCode;
    private final Long memberCode;
    private final String memberId;
    private final Long storeCode;
    private final String storeRepresentativeName;
    private final String storeName;
    private final StoreStatus storeStatus;

    public StoreListResponse(Long sellerCode, Long memberCode, String memberId, Long storeCode, String storeRepresentativeName, String storeName, StoreStatus storeStatus) {
        this.sellerCode = sellerCode;
        this.memberCode = memberCode;
        this.memberId = memberId;
        this.storeCode = storeCode;
        this.storeRepresentativeName = storeRepresentativeName;
        this.storeName = storeName;
        this.storeStatus = storeStatus;
    }

}
