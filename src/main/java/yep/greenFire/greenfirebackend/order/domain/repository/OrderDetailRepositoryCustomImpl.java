//package yep.greenFire.greenfirebackend.order.domain.repository;
//
//import com.querydsl.core.group.GroupBy;
//import com.querydsl.core.types.Projections;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import lombok.RequiredArgsConstructor;
//import yep.greenFire.greenfirebackend.order.dto.response.OrderDetailDTO;
//
//import java.util.List;
//
//import static yep.greenFire.greenfirebackend.order.domain.entity.QOrderDetail.orderDetail;
//import static yep.greenFire.greenfirebackend.product.domain.entity.QProduct.product;
//import static yep.greenFire.greenfirebackend.product.domain.entity.QProductOption.productOption;
//
//@RequiredArgsConstructor
//public class OrderDetailRepositoryCustomImpl implements OrderDetailRepositoryCustom {
//
//
//    private final JPAQueryFactory queryFactory;
//
//    public List<OrderDetailDTO> findByStoreOrderCode(Long storeOrderCode) {
//
//        List<OrderDetailDTO> orderDetails = queryFactory
//                .from(orderDetail)
//                .leftJoin(productOption).on(orderDetail.optionCode.eq(productOption.optionCode))
//                .leftJoin(product).on(productOption.productCode.eq(product.productCode))
//                .where(
//                        orderDetail.storeOrderCode.eq(storeOrderCode)
//                )
//                .transform(
//                        GroupBy.groupBy(orderDetail.storeOrderCode).list(
//                                Projections.constructor(OrderDetailDTO.class,
//                                        orderDetail.orderDetailCode,
//                                        orderDetail.optionCode,
//                                        orderDetail.optionPrice,
//                                        orderDetail.orderQuantity,
//                                        productOption.optionName,
//                                        product.productName,
//                                        product.productImg
//                                )
//                        )
//                );
//
//        return orderDetails;
//
//    }
//}