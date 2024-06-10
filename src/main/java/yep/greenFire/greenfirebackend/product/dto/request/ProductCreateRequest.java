package yep.greenFire.greenfirebackend.product.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import yep.greenFire.greenfirebackend.product.domain.type.SellableStatus;

import java.util.Date;

@Getter
@RequiredArgsConstructor
public class ProductCreateRequest {

    @NotBlank
    private final String productName;
    @Min(value = 1)
    private final Long categoryCode;
    @Min(value = 1)
    private final Long storeCode;
    @Min(value = 0)
    private final Long price;
    private final String productDescription;
    @NotBlank
    private final Date registDate;
    @NotBlank
    private final SellableStatus sellableStatus;

}
