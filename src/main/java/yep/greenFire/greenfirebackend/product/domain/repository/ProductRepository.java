package yep.greenFire.greenfirebackend.product.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import yep.greenFire.greenfirebackend.product.domain.entity.Product;
import yep.greenFire.greenfirebackend.product.domain.type.SellableStatus;
import yep.greenFire.greenfirebackend.product.dto.response.ProductResponse;
import yep.greenFire.greenfirebackend.product.dto.response.ProductsResponse;
import yep.greenFire.greenfirebackend.product.dto.response.SellerProductsResponse;
import yep.greenFire.greenfirebackend.product.presentation.ProductController;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    @Query(
            "select new yep.greenFire.greenfirebackend.product.dto.response.ProductsResponse(p, c, s) " +
                    "from Product p join Category c on c.categoryCode = p.categoryCode " +
                    "join Store s on s.storeCode = p.storeCode " +
                    "where p.categoryCode = :categoryCode " +
                    "and p.sellableStatus = :sellableStatus"
    )
    Page<ProductsResponse> findByCategoryCodeAndSellableStatus(Pageable pageable, Long categoryCode, SellableStatus sellableStatus);

    @Query(
            "select new yep.greenFire.greenfirebackend.product.dto.response.ProductsResponse(p, c, s) " +
                    "from Product p join Category c on c.categoryCode = p.categoryCode " +
                    "join Store s on s.storeCode = p.storeCode " +
                    "where p.storeCode = :storeCode " +
                    "and p.sellableStatus = :sellableStatus"
    )
    Page<ProductsResponse> findByStoreCodeAndSellableStatus(Pageable pageable, Long storeCode, SellableStatus sellableStatus);

    @Query(
            "select new yep.greenFire.greenfirebackend.product.dto.response.ProductsResponse(p, c, s) " +
                    "from Product p join Category c on c.categoryCode = p.categoryCode " +
                    "join Store s on s.storeCode = p.storeCode " +
                    "where p.sellableStatus = :sellableStatus"
    )
    Page<ProductsResponse> findBySellableStatus(Pageable pageable, SellableStatus sellableStatus);

    @Query(
            "select new yep.greenFire.greenfirebackend.product.dto.response.ProductsResponse(p, c, s) " +
                    "from Product p join Category c on c.categoryCode = p.categoryCode " +
                    "join Store s on s.storeCode = p.storeCode " +
                    "where p.productName like %:productName% " +
                    "and p.sellableStatus = :sellableStatus"
    )
    Page<ProductsResponse> findByProductNameContainsAndSellableStatus(Pageable pageable, String productName, SellableStatus sellableStatus);


    /* 판매자 상품 목록 */
    @Query(
            "select new yep.greenFire.greenfirebackend.product.dto.response.SellerProductsResponse(p) " +
                    "from Product p " +
                    "join Store st on st.storeCode = p.storeCode " +
                    "join Seller sl on sl.sellerCode = st.sellerCode " +
                    "where sl.memberCode = :memberCode"
    )
    Page<SellerProductsResponse> findByMemberCode(Pageable pageable, Long memberCode);



}