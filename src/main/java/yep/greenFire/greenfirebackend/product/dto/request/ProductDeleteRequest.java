package yep.greenFire.greenfirebackend.product.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import yep.greenFire.greenfirebackend.product.domain.type.SellableStatus;

@Getter
public class ProductDeleteRequest {

    @NotNull
    private SellableStatus sellablestatus;
}
