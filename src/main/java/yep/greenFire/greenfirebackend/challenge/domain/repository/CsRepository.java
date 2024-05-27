package yep.greenFire.greenfirebackend.challenge.domain.repository;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import yep.greenFire.greenfirebackend.challenge.domain.entity.CsContent;
import yep.greenFire.greenfirebackend.challenge.domain.entity.CsList;
import yep.greenFire.greenfirebackend.challenge.dto.request.CsCreateRequest;

import java.lang.reflect.Member;
import java.util.Optional;

@Repository
public interface CsRepository extends JpaRepository<CsList, Integer> {
    Optional<Object> findByMemberId(String memberId);

    CsContent findByCsCode(int memberCode);

    CsList CsList(CsCreateRequest csCreateRequest);



    // Object findByCsCode(int csCode);
}
