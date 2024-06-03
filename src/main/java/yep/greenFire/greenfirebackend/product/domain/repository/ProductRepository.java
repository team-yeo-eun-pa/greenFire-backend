package yep.greenFire.greenfirebackend.product.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import yep.greenFire.greenfirebackend.product.domain.entity.Product;
import yep.greenFire.greenfirebackend.product.domain.type.SellableStatus;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @EntityGraph(attributePaths = {"category"})
    Page<Product> findByCategoryCodeAndSellableStatus(Pageable pageable, Long categoryCode, SellableStatus sellableStatus);

    @Query(
            "select new yep.greenFire.greenfirebackend.product.dto.response.ProductsResponse(p, c, s, o) " +
                    "from Product p join Category c on c.categoryCode = p.categoryCode " +
                    "join Store s on s.storeCode = p.storeCode " +
                    "left join ProductOption o on o.productCode = p.productCode " +
//                    "where p.productCode = :productCode and p.sellableStatus = 'Y'"
                    "where p.sellableStatus = 'Y'"
    )

    @EntityGraph(attributePaths = {"store"})
    Page<Product> findByStoreCodeAndSellableStatus(Pageable pageable, Long storeCode, SellableStatus sellableStatus);

    Page<Product> findBySellableStatus(Pageable pageable, SellableStatus sellableStatus);


    Page<Product> findByProductNameContainsAndSellableStatus(Pageable pageable, String productName, SellableStatus sellableStatus);

//    Page<Product> findBySellableStatus(Pageable pageable, SellableStatus sellableStatus);
}
