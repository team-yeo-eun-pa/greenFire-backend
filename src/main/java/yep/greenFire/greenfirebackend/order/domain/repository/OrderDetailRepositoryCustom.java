package yep.greenFire.greenfirebackend.order.domain.repository;

import yep.greenFire.greenfirebackend.order.dto.response.OrderDetailDTO;

import java.util.List;

public interface OrderDetailRepositoryCustom {


    List<OrderDetailDTO> findByStoreOrderCode(Long storeOrderCode);

}
