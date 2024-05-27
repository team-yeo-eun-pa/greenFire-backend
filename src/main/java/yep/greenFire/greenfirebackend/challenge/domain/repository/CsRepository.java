package yep.greenFire.greenfirebackend.challenge.domain.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yep.greenFire.greenfirebackend.challenge.domain.entity.CsContent;

import java.lang.reflect.Member;
import java.util.Optional;

@Repository
public interface CsRepository extends JpaRepository<Member, Long> {
    Optional<Object> findByMemberId(String memberId);

    CsContent findByCsCode(int memberCode);

    // Object findByCsCode(int csCode);
}
