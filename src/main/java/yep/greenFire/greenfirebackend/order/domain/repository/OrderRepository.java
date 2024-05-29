package yep.greenFire.greenfirebackend.order.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yep.greenFire.greenfirebackend.order.domain.entity.Order;

public interface OrderRepository extends JpaRepository <Order, Long> {

    /* 이 곳은 JPA를 사용하여 주문 데이터를 관리하는 OrderRepository 입니다
    * 특정 주문 코드와 회원 코드에 해당하는 주문이 존재하는지 여부를 확인할 수 있고,*/
//    boolean existsByOrderCodeAndMemberCode(Long orderCode, Long memberCode);

    /* 특정 회원 코드에 해당하는 주문 목록을 페이징 처리하여 조회할 수 있습니당*/
//    @Query("select new com.ohgiraffers.comprehensive.order.dto.response.OrdersResponse(o, p, r) "+
//            "from Order o join Product p on o.productCode = p.productCode " +
//            "left join Review r on o.orderCode = r.orderCode " +
//            "where o.memberCode = :memberCode")
//    Page<OrdersResponse> findByMemberCode(Pageable pageable, Long memberCode);

}
