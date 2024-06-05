package yep.greenFire.greenfirebackend.order.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yep.greenFire.greenfirebackend.order.domain.entity.DeliveryAddress;
import yep.greenFire.greenfirebackend.product.domain.entity.ProductOption;
import yep.greenFire.greenfirebackend.product.domain.type.ProductOptionAppearActivate;

import java.util.Optional;

public interface DeliveryAddressRepository extends JpaRepository<DeliveryAddress, Long> {
    Optional<DeliveryAddress> findByDeliveryAddressCodeAndMemberCode(Long deliveryAddressCode, Long memberCode);

}
