package yep.greenFire.greenfirebackend.review.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import yep.greenFire.greenfirebackend.order.domain.type.OrderStatus;
import yep.greenFire.greenfirebackend.review.domain.entity.Review;
import yep.greenFire.greenfirebackend.review.domain.type.ReviewStatus;
import yep.greenFire.greenfirebackend.review.dto.response.ReviewResponse;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query(value =
            "SELECT new yep.greenFire.greenfirebackend.review.dto.response.ReviewResponse(" +
            "r.reviewCode, r.productCode, p.productName, r.reviewTitle, r.reviewContent, r.reviewDate, r.modifyDate,r.memberCode, m.memberNickname) " +
            "FROM Review r " +
            "LEFT JOIN Product p ON p.productCode = r.productCode " +
            "LEFT JOIN Member m ON m.memberCode = r.memberCode " +
            "WHERE r.reviewStatus = :reviewStatus " +
            "AND p.productCode = :productCode")
    Page<ReviewResponse> findByProductCode(Pageable pageable, @Param("productCode") Long productCode, @Param("reviewStatus") ReviewStatus reviewStatus);

//    @Query(value = "SELECT new yep.greenFire.greenfirebackend.review.dto.request.ReviewRequest(" +
//            "r.productCode, r.memberCode, r.reviewTitle, r.reviewContent) " +
//            "FROM Review r " +
//            "LEFT JOIN Product p ON p.productCode = r.productCode " +
//            "LEFT JOIN Member m ON m.memberCode = r.memberCode " +
//            "WHERE r.productCode = :productCode " +
//            "AND r.memberCode = :memberCode " +
//            "AND r.orderStatus = :orderStatus")
//    Optional<ReviewResponse> findByMemberCodeAndProductCodeAndOrderStatus(@Param("memberCode")Long memberCode, @Param("productCode") Long productCode, @Param("orderStatus")OrderStatus orderStatus);

}

