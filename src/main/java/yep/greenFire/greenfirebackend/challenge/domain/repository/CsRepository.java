package yep.greenFire.greenfirebackend.challenge.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yep.greenFire.greenfirebackend.challenge.domain.entity.CsContent;

import java.util.Optional;

@Repository
public interface CsRepository extends JpaRepository<CsContent, Integer> {
    Optional<Object> findByMemberId(String memberId);

    CsContent findByMemberCode(int memberCode);

    CsContent findAllCsContents(int csCode);

    Object findByAdminCsCode(int csCode);


    // Object findByCsCode(int csCode);
}
