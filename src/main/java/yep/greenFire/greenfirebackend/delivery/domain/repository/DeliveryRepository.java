package yep.greenFire.greenfirebackend.delivery.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yep.greenFire.greenfirebackend.delivery.domain.entity.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
}
