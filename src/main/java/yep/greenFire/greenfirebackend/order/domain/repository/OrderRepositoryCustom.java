package yep.greenFire.greenfirebackend.order.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import yep.greenFire.greenfirebackend.order.domain.entity.Order;
import yep.greenFire.greenfirebackend.order.dto.response.OrderResponse;

import java.util.Optional;

public interface OrderRepositoryCustom {

    Optional<OrderResponse> findOrderResponseByOrderCode(Long orderCode);

    Page<OrderResponse> findByMemberCode(Long memberCode, Pageable pageable);

}
