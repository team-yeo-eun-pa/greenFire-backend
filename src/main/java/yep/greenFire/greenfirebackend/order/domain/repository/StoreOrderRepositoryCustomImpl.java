package yep.greenFire.greenfirebackend.order.domain.repository;

import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import yep.greenFire.greenfirebackend.order.domain.type.OrderStatus;
import yep.greenFire.greenfirebackend.order.dto.response.OrderResponse;
import yep.greenFire.greenfirebackend.order.dto.response.StoreOrderDTO;

import java.util.List;

import static yep.greenFire.greenfirebackend.delivery.domain.entity.QDelivery.delivery;
import static yep.greenFire.greenfirebackend.order.domain.entity.QOrder.order;
import static yep.greenFire.greenfirebackend.order.domain.entity.QStoreOrder.storeOrder;
import static yep.greenFire.greenfirebackend.payment.domain.entity.QPayment.payment;
import static yep.greenFire.greenfirebackend.store.domain.entity.QStore.store;

@RequiredArgsConstructor
public class StoreOrderRepositoryCustomImpl implements StoreOrderRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    // 스토어 - 주문 조회
    public Page<OrderResponse> findByStoreCode(Long storeCode, Pageable pageable) {

        List<OrderResponse> orders = queryFactory
                .from(order)
                .leftJoin(order.storeOrders, storeOrder)
                .leftJoin(payment).on(payment.orderCode.eq(order.orderCode))
                .leftJoin(store).on(storeOrder.storeCode.eq(store.storeCode))
                .leftJoin(delivery).on(delivery.storeOrderCode.eq(storeOrder.storeOrderCode))
                .where(
                        storeOrder.storeCode.eq(storeCode)
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
                                                storeOrder.storeCode,
                                                store.storeName,
                                                storeOrder.orderStatus,
                                                delivery.deliveryCompany,
                                                delivery.transportNumber,
                                                delivery.deliveryDate,
                                                delivery.deliveryStatus,
                                                storeOrder.rejectionDate,
                                                storeOrder.rejectionReason
                                        ))
                                )
                        )
                );

        JPAQuery<Long> countQuery = queryFactory
                .select(order.orderCode.countDistinct())
                .from(order)
                .leftJoin(order.storeOrders, storeOrder)
                .where(
                        storeOrder.storeCode.eq(storeCode)
                );

        return PageableExecutionUtils.getPage(orders, pageable, countQuery::fetchOne);

    }

    // 스토어 - 주문 상태에 따른 조회
    @Override
    public Page<OrderResponse> findByStoreCodeAndOrderStatus(Long storeCode, List<OrderStatus> orderStatuses, Pageable pageable) {

        List<OrderResponse> orders = queryFactory
                .from(order)
                .leftJoin(order.storeOrders, storeOrder)
                .leftJoin(payment).on(payment.orderCode.eq(order.orderCode))
                .leftJoin(store).on(storeOrder.storeCode.eq(store.storeCode))
                .leftJoin(delivery).on(delivery.storeOrderCode.eq(storeOrder.storeOrderCode))
                .where(
                        storeOrder.storeCode.eq(storeCode)
                                .and(storeOrder.orderStatus.in(orderStatuses))
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
                                                storeOrder.storeCode,
                                                store.storeName,
                                                storeOrder.orderStatus,
                                                delivery.deliveryCompany,
                                                delivery.transportNumber,
                                                delivery.deliveryDate,
                                                delivery.deliveryStatus,
                                                storeOrder.rejectionDate,
                                                storeOrder.rejectionReason
                                        ))
                                )
                        )
                );

        JPAQuery<Long> countQuery = queryFactory
                .select(order.orderCode.countDistinct())
                .from(order)
                .leftJoin(order.storeOrders, storeOrder)
                .where(
                        storeOrder.storeCode.eq(storeCode)
                                .and(storeOrder.orderStatus.in(orderStatuses))
                );

        return PageableExecutionUtils.getPage(orders, pageable, countQuery::fetchOne);

    }

}
