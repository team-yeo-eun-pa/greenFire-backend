package yep.greenFire.greenfirebackend.order.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yep.greenFire.greenfirebackend.order.domain.entity.OrderDetail;

public interface OrderDetailRepository extends JpaRepository <OrderDetail, Long>, OrderDetailRepositoryCustom {

}
