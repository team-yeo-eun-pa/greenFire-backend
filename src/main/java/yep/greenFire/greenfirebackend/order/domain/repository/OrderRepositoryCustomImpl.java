package yep.greenFire.greenfirebackend.order.domain.repository;

import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import yep.greenFire.greenfirebackend.order.dto.response.OrderResponse;
import yep.greenFire.greenfirebackend.order.dto.response.StoreOrderDTO;

import java.util.List;

import static yep.greenFire.greenfirebackend.delivery.domain.entity.QDelivery.delivery;
import static yep.greenFire.greenfirebackend.order.domain.entity.QOrder.order;
import static yep.greenFire.greenfirebackend.order.domain.entity.QStoreOrder.storeOrder;
import static yep.greenFire.greenfirebackend.payment.domain.entity.QPayment.payment;
import static yep.greenFire.greenfirebackend.store.domain.entity.QStore.store;

@RequiredArgsConstructor
public class OrderRepositoryCustomImpl implements OrderRepositoryCustom {


    private final JPAQueryFactory queryFactory;

    // 회원 - 주문 조회
    public Page<OrderResponse> findByMemberCode(Long memberCode, Pageable pageable) {

        List<OrderResponse> orders = queryFactory
                .from(order)
                .leftJoin(order.storeOrders, storeOrder)
                .leftJoin(payment).on(payment.orderCode.eq(order.orderCode))
                .leftJoin(store).on(storeOrder.storeCode.eq(store.storeCode))
                .leftJoin(delivery).on(delivery.storeOrderCode.eq(storeOrder.storeOrderCode))
                .where(
                        order.memberCode.eq(memberCode)
                )
                .transform(
                        GroupBy.groupBy(order.orderCode).list(
                                Projections.constructor(OrderResponse.class,
                                        order.orderCode,
                                        order.memberCode,
                                        order.orderName,
                                        order.receiverName,
                                        order.contactNumber,
                                        order.addressZonecode,
                                        order.address,
                                        order.addressDetail,
                                        order.deliveryRequest,
                                        order.totalOrderAmount,
                                        order.totalDiscountAmount,
                                        order.totalDeliveryAmount,
                                        order.totalRealPayment,
                                        order.orderDate,
                                        payment.paymentWay,
                                        GroupBy.list(Projections.constructor(StoreOrderDTO.class,
                                                storeOrder.storeOrderCode,
                                                store.storeName,
                                                storeOrder.orderStatus,
                                                delivery.deliveryCompany,
                                                delivery.transportNumber,
                                                delivery.deliveryDate,
                                                delivery.deliveryStatus
                                        ))
                                )
                        )
                );

        JPAQuery<Long> countQuery = queryFactory
                .select(order.orderCode.countDistinct())
                .from(order)
                .where(
                        order.memberCode.eq(memberCode)
                );

        return PageableExecutionUtils.getPage(orders, pageable, countQuery::fetchOne);

    }
}