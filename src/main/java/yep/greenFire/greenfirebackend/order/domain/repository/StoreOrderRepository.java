package yep.greenFire.greenfirebackend.order.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yep.greenFire.greenfirebackend.order.domain.entity.StoreOrder;
import yep.greenFire.greenfirebackend.order.domain.type.OrderStatus;

import java.util.Optional;

public interface StoreOrderRepository extends JpaRepository <StoreOrder, Long>, StoreOrderRepositoryCustom {

    Optional<StoreOrder> findByStoreOrderCodeAndOrderStatus(Long storeOrderCode, OrderStatus orderStatus);
}
