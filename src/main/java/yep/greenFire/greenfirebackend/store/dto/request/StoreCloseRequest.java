package yep.greenFire.greenfirebackend.store.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import yep.greenFire.greenfirebackend.store.domain.type.StoreStatus;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class StoreCloseRequest {

    @NotNull
    private final LocalDateTime suspendedEndDate;

    @NotNull
    private final StoreStatus storeStatus;

}
