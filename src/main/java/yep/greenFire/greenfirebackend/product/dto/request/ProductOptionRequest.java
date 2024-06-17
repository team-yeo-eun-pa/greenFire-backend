package yep.greenFire.greenfirebackend.product.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProductOptionRequest {

    ProductOptionUpdateRequest productOptionUpdateRequest;

    ProductOptionDeleteRequest productOptionDeleteRequest;
}
