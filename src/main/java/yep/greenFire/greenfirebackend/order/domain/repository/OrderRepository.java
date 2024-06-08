package yep.greenFire.greenfirebackend.order.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import yep.greenFire.greenfirebackend.order.domain.entity.Order;
import yep.greenFire.greenfirebackend.order.dto.response.OrderResponse;

import java.util.List;

public interface OrderRepository extends JpaRepository <Order, Long> {

    /* 특정 회원 코드 주문 목록 조회 */
//    Page<Order> findByMemberCode (Long memberCode, Pageable pageable);

    /* 특정 회원 코드 주문 목록 조회 */
    @Query("select distinct new yep.greenFire.greenfirebackend.order.dto.response.OrderResponse(o, po, p) " +
            "from Order o " +
            "join o.storeOrders so " +
            "join so.orderDetails od " +
            "join ProductOption po on od.optionCode = po.optionCode " +
            "join Product p on po.productCode = p.productCode " +
//            "join Store s on p.storeCode = s.storeCode" +
            "where o.memberCode = :memberCode")
    Page<OrderResponse> findByMemberCode(@Param("memberCode") Long memberCode, Pageable pageable);

    /* 특정 회원 코드 주문 목록 상세 조회 */
    @Query("select distinct new yep.greenFire.greenfirebackend.order.dto.response.OrderResponse(o, po, p) " +
            "from Order o " +
            "join o.storeOrders so " +
            "join so.orderDetails od " +
            "join ProductOption po on od.optionCode = po.optionCode " +
            "join Product p on po.productCode = p.productCode " +
//            "join Store s on p.storeCode = s.storeCode" +
            "where o.memberCode = :memberCode and o.orderCode = :orderCode")
    List<OrderResponse> findByMemberCodeAndOrderCode(@Param("memberCode") Long memberCode, @Param("orderCode")Long orderCode);


    /* 특정 주문 코드와 회원 코드에 해당하는 주문이 존재하는지 여부 확인*/
//    boolean existsByOrderCodeAndMemberCode(Long orderCode, Long memberCode);

}
