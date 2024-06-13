package yep.greenFire.greenfirebackend.order.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import yep.greenFire.greenfirebackend.order.domain.entity.Order;
import yep.greenFire.greenfirebackend.order.domain.type.OrderStatus;
import yep.greenFire.greenfirebackend.order.dto.response.OrderResponse;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    /* 특정 주문 조회 */
    Optional<Order> findByOrderCode(Long orderCode);

    /* 특정 회원 코드 주문 목록 조회 */
    @Query("select distinct new yep.greenFire.greenfirebackend.order.dto.response.OrderResponse(o, po, p, s, pm) " +
            "from Order o " +
            "join o.storeOrders so " +
            "join so.orderDetails od " +
            "join ProductOption po on od.optionCode = po.optionCode " +
            "join Product p on po.productCode = p.productCode " +
            "join Store s on p.storeCode = s.storeCode " +
            "join Payment pm on pm.orderCode = o.orderCode " +
            "where o.memberCode = :memberCode")
    Page<OrderResponse> findByMemberCode(@Param("memberCode") Long memberCode, Pageable pageable);
//
//    @Query("SELECT o FROM Order o JOIN OrderDetail od ON o.orderCode = od.storeOrderCode " +
//            "WHERE od.storeOrderCode = :storeCode AND od.isOrderCancel = false AND o.orderStatus IN :statuses")
//    List<Order> findActiveOrders(@Param("storeCode") Long storeCode, @Param("statuses") List<OrderStatus> statuses);



    /* 특정 스토어 코드 주문 목록 조회 */
    @Query("select distinct new yep.greenFire.greenfirebackend.order.dto.response.OrderResponse(o, po, p, s, pm) " +
            "from Order o " +
            "join o.storeOrders so " +
            "join so.orderDetails od " +
            "join ProductOption po on od.optionCode = po.optionCode " +
            "join Product p on po.productCode = p.productCode " +
            "join Store s on p.storeCode = s.storeCode " +
            "join Payment pm on pm.orderCode = o.orderCode " +
            "where s.storeCode = :storeCode")
    Page<OrderResponse> findByStoreCode(@Param("storeCode") Long storeCode, Pageable pageable);



    /* 특정 회원 코드 주문 목록 상세 조회 */
//    @Query("select distinct new yep.greenFire.greenfirebackend.order.dto.response.OrderResponse(o, po, p) " +
//            "from Order o " +
//            "join o.storeOrders so " +
//            "join so.orderDetails od " +
//            "join ProductOption po on od.optionCode = po.optionCode " +
//            "join Product p on po.productCode = p.productCode " +
////            "join Store s on p.storeCode = s.storeCode" +
//            "where o.memberCode = :memberCode and o.orderCode = :orderCode")
//    List<OrderResponse> findByMemberCodeAndOrderCode(@Param("memberCode") Long memberCode, @Param("orderCode") Long orderCode);
//

    /* 특정 주문 코드와 회원 코드에 해당하는 주문이 존재하는지 여부 확인*/
//    boolean existsByOrderCodeAndMemberCode(Long orderCode, Long memberCode);

}
