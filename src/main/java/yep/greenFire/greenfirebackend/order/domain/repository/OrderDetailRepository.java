package yep.greenFire.greenfirebackend.order.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yep.greenFire.greenfirebackend.order.domain.entity.OrderDetail;
import yep.greenFire.greenfirebackend.order.domain.type.OrderStatus;

import java.util.Optional;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {


}
