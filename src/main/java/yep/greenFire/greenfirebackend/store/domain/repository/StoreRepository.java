package yep.greenFire.greenfirebackend.store.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yep.greenFire.greenfirebackend.store.domain.entity.Store;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {

    /* 스토어 상세 조회 : storeCode로 스토어 1개 조회 */
    /*Optional을 사용하면 조회 결과가 없을 때 null 대신 Optional.empty()를 반환하므로 더 안전함.*/
    Optional<Store> findByStoreCode(Long storeCode);

}
