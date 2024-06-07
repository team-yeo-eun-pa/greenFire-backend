package yep.greenFire.greenfirebackend.seller.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yep.greenFire.greenfirebackend.seller.domain.entity.Seller;

public interface SellerRepository extends JpaRepository<Seller, Long> {


}
