package yep.greenFire.greenfirebackend.apply.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import yep.greenFire.greenfirebackend.apply.domain.type.ApplyStatus;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AdminApplyResponse {

    private final Long sellerCode;
    private final Long memberCode;
    private final String storeName;
    private final String storeRepresentativeName;
    private final String businessImg;
    private final String businessNumber;
    private final String mosNumber;
    private final String storeType;
    private final String memberPhone;
    private final String applyContent;
    private final ApplyStatus applyStatus;
    private final LocalDateTime applyDatetime;
    private final LocalDateTime applyProcessingDate;
    private final LocalDateTime applyCancelDate;
    private final String rejectReason;

}
