package yep.greenFire.greenfirebackend.order.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yep.greenFire.greenfirebackend.order.domain.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>, OrderRepositoryCustom {

}

