package yep.greenFire.greenfirebackend.seller.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import yep.greenFire.greenfirebackend.seller.domain.entity.Seller;
import yep.greenFire.greenfirebackend.seller.dto.response.StoreListResponse;
import yep.greenFire.greenfirebackend.seller.dto.response.StoreProfileResponse;

import java.util.List;

public interface SellerRepository extends JpaRepository<Seller, Long> {

    // 판매자 보유 스토어 목록 조회
    @Query("SELECT new yep.greenFire.greenfirebackend.seller.dto.response.StoreListResponse" +
            "(s.sellerCode, m.memberCode, m.memberId, st.storeCode, s.storeRepresentativeName, st.storeName, st.storeStatus) " +
            "FROM Seller s " +
            "LEFT JOIN Member m ON s.memberCode = m.memberCode " +
            "LEFT JOIN Store st ON s.sellerCode = st.sellerCode " +
            "WHERE m.memberCode = :memberCode AND s.applyStatus = yep.greenFire.greenfirebackend.seller.domain.type.ApplyStatus.APPLY")
    List<StoreListResponse> findByMemberCode(@Param("memberCode") Long memberCode);

    // 특정 스토어 프로필 조회
    @Query("SELECT new yep.greenFire.greenfirebackend.seller.dto.response.StoreProfileResponse" +
            "(st.storeCode, s.sellerCode, m.memberCode, s.storeRepresentativeName, st.storeName, s.businessNumber, s.mosNumber, s.businessImg, s.storeType, st.storeInfo) " +
            "FROM Store st " +
            "LEFT JOIN Seller s ON s.sellerCode = st.sellerCode " +
            "LEFT JOIN Member m ON s.memberCode = m.memberCode " +
            "WHERE st.storeCode = :storeCode AND m.memberCode = s.memberCode AND st.storeStatus = yep.greenFire.greenfirebackend.store.domain.type.StoreStatus.OPEN")
    StoreProfileResponse findByStoreCode(@Param("storeCode") Long storeCode);


}
