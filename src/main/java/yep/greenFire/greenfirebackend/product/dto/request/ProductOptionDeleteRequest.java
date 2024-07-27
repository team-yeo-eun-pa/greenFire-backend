package yep.greenFire.greenfirebackend.product.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import yep.greenFire.greenfirebackend.product.domain.type.ProductOptionAppearActivate;


@Getter
public class ProductOptionDeleteRequest {

    @NotNull
    private ProductOptionAppearActivate optionAppearActivate;
}
