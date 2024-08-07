package yep.greenFire.greenfirebackend.store.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import yep.greenFire.greenfirebackend.store.domain.entity.Store;
import yep.greenFire.greenfirebackend.store.domain.type.StoreStatus;
import yep.greenFire.greenfirebackend.store.dto.response.StoreListResponse;
import yep.greenFire.greenfirebackend.store.dto.response.StoreProfileResponse;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {

    /* 스토어 상세 조회 : storeCode로 스토어 1개 조회 */
    /*Optional을 사용하면 조회 결과가 없을 때 null 대신 Optional.empty()를 반환하므로 더 안전함.*/
    Optional<Store> findByStoreCode(Long storeCode);

    // 스토어 승인 후 신규 등록
    Optional<Store> findStoreForPreOpenUpdateBySellerCodeAndStoreStatus(@Param("sellerCode")Long sellerCode, StoreStatus storeStatus);

    // 판매자 보유 스토어 목록 조회
    @Query("SELECT new yep.greenFire.greenfirebackend.store.dto.response.StoreListResponse" +
            "(s.sellerCode, m.memberCode, m.memberId, st.storeCode, s.storeRepresentativeName, st.storeName, st.storeStatus) " +
            "FROM Seller s " +
            "LEFT JOIN Store st ON s.sellerCode = st.sellerCode " +
            "LEFT JOIN Member m ON s.memberCode = m.memberCode " +
            "WHERE m.memberCode = :memberCode AND s.applyStatus = yep.greenFire.greenfirebackend.apply.domain.type.ApplyStatus.APPLY " +
            "ORDER BY CASE WHEN st.storeStatus = 'PRE_OPEN' THEN 0 ELSE 1 END, st.storeName ASC")
    List<StoreListResponse> findByMemberCode(@Param("memberCode") Long memberCode);

    // 특정 스토어 프로필 조회
    @Query("SELECT new yep.greenFire.greenfirebackend.store.dto.response.StoreProfileResponse" +
            "(st.storeCode, st.sellerCode, st.storeName, st.storeInfo, st.addressZonecode, st.addressType, st.address, " +
            "st.addressDetail, st.deliveryAmount, st.freeDeliveryCondition, st.reportCount, st.suspendedEndDate, st.storeStatus, " +
            "s.storeRepresentativeName, s.businessNumber, s.mosNumber, s.businessImg, s.applyContent, s.storeType, s.applyDatetime, " +
            "s.applyProcessingDate, s.applyCancelDate, s.rejectReason, s.applyStatus) " +
            "FROM Store st " +
            "LEFT JOIN Seller s ON s.sellerCode = st.sellerCode " +
            "WHERE st.sellerCode = :sellerCode AND s.applyStatus = 'APPLY'")
    Optional<StoreProfileResponse> findBySellerCode(@Param("sellerCode") Long sellerCode);

    // 스토어 프로필 수정
    Optional<Store> findStoreForOpenUpdateBySellerCodeAndStoreStatus(@Param("sellerCode")Long sellerCode, StoreStatus storeStatus);

    // 미사용 시 삭제 예정
    Optional<Store> findByStoreCodeAndStoreStatus(Long storeCode, StoreStatus storeStatus);


    //MemberCode로 스토어 찾기
    @Query("SELECT s.storeCode FROM Store s " +
            "JOIN Seller sl ON s.sellerCode = sl.sellerCode " +
            "JOIN Member m ON sl.memberCode = m.memberCode " +
            "WHERE m.memberCode = :memberCode")
    Long findStoreByMemberCode(Long memberCode);
}

