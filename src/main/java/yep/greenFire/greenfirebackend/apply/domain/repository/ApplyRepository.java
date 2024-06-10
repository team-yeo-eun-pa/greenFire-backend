package yep.greenFire.greenfirebackend.apply.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import yep.greenFire.greenfirebackend.apply.domain.type.ApplyStatus;
import yep.greenFire.greenfirebackend.apply.dto.response.AdminApplyResponse;
import yep.greenFire.greenfirebackend.apply.dto.response.ApplyResponse;
import yep.greenFire.greenfirebackend.seller.domain.entity.Seller;

import java.util.Optional;

public interface ApplyRepository extends JpaRepository<Seller, Long> {

    /* member --------------------- */

    // 입점 신청 내역 전체 조회
    @Query("SELECT new yep.greenFire.greenfirebackend.apply.dto.response.ApplyResponse" +
            "(s.sellerCode, m.memberCode, s.storeName, s.storeRepresentativeName, s.businessImg, s.businessNumber, s.mosNumber, s.storeType, m.memberPhone, s.applyContent, s.applyStatus, s.applyDatetime, s.applyProcessingDate, s.applyCancelDate, s.rejectReason) " +
            "FROM Seller s LEFT JOIN Member m on s.memberCode = m.memberCode " +
            "WHERE m.memberCode = :memberCode")
    Page<ApplyResponse> findByMemberCode(Pageable pageable, @Param("memberCode") Long memberCode);

    // 입점 신청 내역 상세 조회
    @Query("SELECT new yep.greenFire.greenfirebackend.apply.dto.response.ApplyResponse" +
            "(s.sellerCode, m.memberCode, s.storeName, s.storeRepresentativeName, s.businessImg, s.businessNumber, s.mosNumber, s.storeType, m.memberPhone, s.applyContent, s.applyStatus, s.applyDatetime, s.applyProcessingDate, s.applyCancelDate, s.rejectReason) " +
            "FROM Seller s LEFT JOIN Member m on s.memberCode = m.memberCode " +
            "WHERE s.memberCode = :memberCode AND s.sellerCode = :sellerCode ")
    Optional<ApplyResponse> findByMemberCodeAndSellerCode(@Param("memberCode") Long memberCode, @Param("sellerCode") Long sellerCode);

    // 입점 신청 내용 수정
    Optional<Seller> findBySellerCodeAndApplyStatusNot(Long sellerCode, ApplyStatus applyStatus);

    // 입점 신청 취소 & 관리자 승인 및 반려
    Optional<Seller> findBySellerCodeAndApplyStatus(Long sellerCode, ApplyStatus applyStatus);


    /* admin --------------------- */

    // 입점 신청 내역 전체 조회
    @Query("SELECT new yep.greenFire.greenfirebackend.apply.dto.response.AdminApplyResponse" +
            "(s.sellerCode, m.memberCode, s.storeName, s.storeRepresentativeName, s.businessImg, s.businessNumber, s.mosNumber, s.storeType, m.memberPhone, s.applyContent, s.applyStatus, s.applyDatetime, s.applyProcessingDate, s.applyCancelDate, s.rejectReason) " +
            "FROM Seller s LEFT JOIN Member m on s.memberCode = m.memberCode " +
            "WHERE s.applyStatus = :applyStatus")
    Page<AdminApplyResponse> getAdminApplies(Pageable pageable, @Param("applyStatus") ApplyStatus applyStatus);

    // 입점 신청 내역 상세 조회
    @Query("SELECT new yep.greenFire.greenfirebackend.apply.dto.response.AdminApplyResponse" +
            "(s.sellerCode, m.memberCode, s.storeName, s.storeRepresentativeName, s.businessImg, s.businessNumber, s.mosNumber, s.storeType, m.memberPhone, s.applyContent, s.applyStatus, s.applyDatetime, s.applyProcessingDate, s.applyCancelDate, s.rejectReason) " +
            "FROM Seller s LEFT JOIN Member m on s.memberCode = m.memberCode " +
            "WHERE s.sellerCode = :sellerCode AND s.applyStatus = :applyStatus")
    Optional<AdminApplyResponse> getApplyDetail(@Param("sellerCode") Long sellerCode, @Param("applyStatus") ApplyStatus applyStatus);

    // 신청 승인 및 반려
    @Modifying
    @Query("UPDATE Seller s SET s.applyStatus = :newStatus WHERE s.sellerCode = :sellerCode AND s.applyStatus = :excludedStatus")
    int updateApplyStatus(@Param("sellerCode") Long sellerCode, @Param("newStatus") ApplyStatus newStatus, @Param("excludedStatus") ApplyStatus excludedStatus);
}
