package yep.greenFire.greenfirebackend.product.domain.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;
import yep.greenFire.greenfirebackend.product.domain.entity.Product;
import yep.greenFire.greenfirebackend.product.domain.type.SellableStatus;
import yep.greenFire.greenfirebackend.product.dto.ProductDTO;
import yep.greenFire.greenfirebackend.product.dto.response.ProductResponse;
import yep.greenFire.greenfirebackend.product.dto.response.ProductsResponse;
import yep.greenFire.greenfirebackend.product.dto.response.SellerProductResponse;
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


    /* 상품 상세 조회 */
    @Query(
            "select new yep.greenFire.greenfirebackend.product.dto.ProductDTO(p, c, s) " +
                    "from Product p join Category c on c.categoryCode = p.categoryCode " +
                    "join Store s on s.storeCode = p.storeCode " +
                    "where p.productCode = :productCode " +
                    "and p.sellableStatus = :sellableStatus"
    )
    Optional<ProductDTO> findByProductCodeAndSellableStatus(Long productCode, SellableStatus sellableStatus);



    /* 판매자 상품 목록 */
    @Query(
            "select new yep.greenFire.greenfirebackend.product.dto.response.SellerProductsResponse(p) " +
                    "from Product p " +
                    "join Store st on st.storeCode = p.storeCode " +
                    "join Seller sl on sl.sellerCode = st.sellerCode " +
                    "where sl.memberCode = :memberCode " +
                    "and p.sellableStatus != :sellableStatus"
    )
    Page<SellerProductsResponse> findByMemberCodeAndSellableStatusNot(Pageable pageable, Long memberCode, SellableStatus sellableStatus);

    @Query(
            "select new yep.greenFire.greenfirebackend.product.dto.ProductDTO(p, c, st) " +
                    "from Product p " +
                    "join Store st on st.storeCode = p.storeCode " +
                    "join Seller sl on sl.sellerCode = st.sellerCode " +
                    "join Category c on c.categoryCode = p.categoryCode " +
                    "where sl.memberCode = :memberCode " +
                    "and p.productCode = :productCode"
    )
    Optional<ProductDTO> findByProductCodeAndMemberCode(Long productCode, Long memberCode);

    /* 판매자 상품 삭제 */

    Optional<Product> findByProductCodeAndSellableStatusNot(Long productCode, SellableStatus sellableStatus);

    boolean existsByProductCode (Long productCode);

}