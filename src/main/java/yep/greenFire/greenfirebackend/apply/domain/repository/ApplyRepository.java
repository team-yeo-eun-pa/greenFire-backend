package yep.greenFire.greenfirebackend.apply.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import yep.greenFire.greenfirebackend.apply.dto.response.ApplyResponse;
import yep.greenFire.greenfirebackend.seller.domain.entity.Seller;

public interface ApplyRepository extends JpaRepository<Seller, Long> {

    @Query("SELECT new yep.greenFire.greenfirebackend.apply.dto.response.ApplyResponse" +
            "(s.sellerCode, m.memberCode, s.storeName, s.storeRepresentativeName, s.businessImg, s.businessNumber, s.mosNumber, s.storeType, m.memberPhone, s.applyContent, s.applyStatus, s.applyDatetime, s.applyProcessingDate, s.applyCancelDate, s.rejectReason) " +
            "FROM Seller s LEFT JOIN Member m on s.memberCode = m.memberCode " +
            "WHERE m.memberCode = :memberCode")
    Page<ApplyResponse> findByMemberCode(Pageable pageable, @Param("memberCode") Long memberCode);


}
