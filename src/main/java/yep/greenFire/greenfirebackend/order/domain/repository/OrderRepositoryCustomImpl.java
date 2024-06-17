package yep.greenFire.greenfirebackend.order.domain.repository;

import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import yep.greenFire.greenfirebackend.common.exception.NotFoundException;
import yep.greenFire.greenfirebackend.common.exception.type.ExceptionCode;
import yep.greenFire.greenfirebackend.order.dto.response.OrderResponse;
import yep.greenFire.greenfirebackend.order.dto.response.StoreOrderDTO;

import java.util.List;
import java.util.Optional;

import static yep.greenFire.greenfirebackend.delivery.domain.entity.QDelivery.delivery;
import static yep.greenFire.greenfirebackend.order.domain.entity.QOrder.order;
import static yep.greenFire.greenfirebackend.order.domain.entity.QStoreOrder.storeOrder;
import static yep.greenFire.greenfirebackend.payment.domain.entity.QPayment.payment;
import static yep.greenFire.greenfirebackend.store.domain.entity.QStore.store;

@RequiredArgsConstructor
public class OrderRepositoryCustomImpl implements OrderRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    // memberCode 기준 조회
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
                                        order.isOrderCancel,
                                        order.orderDate,
                                        payment.paymentWay,
                                        GroupBy.list(Projections.constructor(StoreOrderDTO.class,
                                                storeOrder.storeOrderCode,
                                                storeOrder.storeCode,
                                                store.sellerCode,
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
                .where(
                        order.memberCode.eq(memberCode)
                );

        return PageableExecutionUtils.getPage(orders, pageable, countQuery::fetchOne);

    }

    // orderCode 기준 조회
    public Optional<OrderResponse> findOrderResponseByOrderCode(Long orderCode) {
        OrderResponse orderResponse = queryFactory
                .from(order)
                .leftJoin(order.storeOrders, storeOrder)
                .leftJoin(payment).on(payment.orderCode.eq(order.orderCode))
                .leftJoin(store).on(storeOrder.storeCode.eq(store.storeCode))
                .leftJoin(delivery).on(delivery.storeOrderCode.eq(storeOrder.storeOrderCode))
                .where(
                        order.orderCode.eq(orderCode)
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
                                        order.isOrderCancel,
                                        order.orderDate,
                                        payment.paymentWay,
                                        GroupBy.list(Projections.constructor(StoreOrderDTO.class,
                                                storeOrder.storeOrderCode,
                                                storeOrder.storeCode,
                                                store.sellerCode,
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
                )
                .stream()
                .findFirst()
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_VALID_ORDER));

        return Optional.ofNullable(orderResponse);
    }

}
