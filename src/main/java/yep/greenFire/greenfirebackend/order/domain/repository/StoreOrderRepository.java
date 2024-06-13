package yep.greenFire.greenfirebackend.order.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yep.greenFire.greenfirebackend.order.domain.entity.StoreOrder;

public interface StoreOrderRepository extends JpaRepository <StoreOrder, Long>, StoreOrderRepositoryCustom {

}
