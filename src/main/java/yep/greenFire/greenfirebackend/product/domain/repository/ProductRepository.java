package yep.greenFire.greenfirebackend.product.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import yep.greenFire.greenfirebackend.product.domain.entity.Product;
import yep.greenFire.greenfirebackend.product.domain.type.SellableStatus;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByCategoryCode(Pageable pageable, Long categoryCode, SellableStatus sellableStatus);

    Page<Product> findByStoreCode(Pageable pageable, Long storeCode, SellableStatus sellableStatus);

    Page<Product> findByProductName(Pageable pageable, String productName, SellableStatus sellableStatus);

    Page<Product> findByStatus(Pageable pageable, SellableStatus sellableStatus);
}
