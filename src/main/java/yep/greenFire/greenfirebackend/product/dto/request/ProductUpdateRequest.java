package yep.greenFire.greenfirebackend.product.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import yep.greenFire.greenfirebackend.product.domain.type.SellableStatus;

import java.util.Date;

@Getter
@RequiredArgsConstructor
public class ProductUpdateRequest {

    @NotBlank
    private final String productName;
    @Min(value = 1)
    private final Long categoryCode;
    @Min(value = 1)
    private final Long storeCode;
    private final String productDescription;
    @NotNull
    private final SellableStatus sellableStatus;
    private final String productImg;

}