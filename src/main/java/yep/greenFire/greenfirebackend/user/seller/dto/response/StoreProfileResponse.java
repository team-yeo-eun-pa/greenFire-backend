package yep.greenFire.greenfirebackend.user.seller.dto.response;

import lombok.Getter;

@Getter

public class StoreProfileResponse {

    private final Long storeCode;
    private final Long sellerCode;
    private final Long memberCode;
    private final String storeRepresentativeName;
    private final String storeName;
    private final String businessNumber;
    private final String mosNumber;
    private final String businessImg;
    private final String storeType;
    private final String storeInfo;

    public StoreProfileResponse(Long storeCode, Long sellerCode, Long memberCode, String storeRepresentativeName, String storeName, String businessNumber, String mosNumber, String businessImg, String storeType, String storeInfo) {
        this.storeCode = storeCode;
        this.sellerCode = sellerCode;
        this.memberCode = memberCode;
        this.storeRepresentativeName = storeRepresentativeName;
        this.storeName = storeName;
        this.businessNumber = businessNumber;
        this.mosNumber = mosNumber;
        this.businessImg = businessImg;
        this.storeType = storeType;
        this.storeInfo = storeInfo;
    }
}
