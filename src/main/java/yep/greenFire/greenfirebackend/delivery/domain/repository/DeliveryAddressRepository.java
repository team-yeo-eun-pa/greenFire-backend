package yep.greenFire.greenfirebackend.delivery.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yep.greenFire.greenfirebackend.delivery.domain.entity.DeliveryAddress;

import java.util.List;
import java.util.Optional;

public interface DeliveryAddressRepository extends JpaRepository<DeliveryAddress, Long> {

    // 회원 배송지 목록 조회
    List<DeliveryAddress> findByMemberCode(Long memberCode);

    Optional<DeliveryAddress> findByDeliveryAddressCodeAndMemberCode(Long deliveryAddressCode, Long memberCode);

}
