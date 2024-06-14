package yep.greenFire.greenfirebackend.order.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import yep.greenFire.greenfirebackend.order.domain.type.OrderStatus;
import yep.greenFire.greenfirebackend.order.dto.response.OrderResponse;

import java.util.List;

public interface StoreOrderRepositoryCustom {

    Page<OrderResponse> findByStoreCode(Long storeCode, Pageable pageable);

    Page<OrderResponse> findByStoreCodeAndOrderStatus(Long storeCode, List<OrderStatus> orderStatus, Pageable pageable);

}
