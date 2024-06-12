package yep.greenFire.greenfirebackend.order.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import yep.greenFire.greenfirebackend.order.dto.response.OrderResponse;

public interface StoreOrderRepositoryCustom {

    Page<OrderResponse> findByStoreCode(Long storeCode, Pageable pageable);

}
