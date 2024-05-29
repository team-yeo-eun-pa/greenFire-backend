package yep.greenFire.greenfirebackend.order.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yep.greenFire.greenfirebackend.order.domain.entity.DeliveryAddress;
import yep.greenFire.greenfirebackend.order.domain.entity.Order;
import yep.greenFire.greenfirebackend.order.domain.entity.OrderDetail;
import yep.greenFire.greenfirebackend.order.domain.entity.StoreOrder;
import yep.greenFire.greenfirebackend.order.domain.repository.DeliveryAddressRepository;
import yep.greenFire.greenfirebackend.order.domain.repository.OrderRepository;
import yep.greenFire.greenfirebackend.order.dto.request.OrderCreateRequest;
import yep.greenFire.greenfirebackend.product.domain.entity.ProductOption;
import yep.greenFire.greenfirebackend.product.service.ProductOptionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    /* 재고 수량 테이블에서 재고를 조절한다. */
    private final ProductOptionService productOptionService;
    /* 배송지 테이블에서 등록된 배송지를 받아온다. */
    private final DeliveryAddressRepository deliveryAddressRepository;
    /*  */
    private final OrderRepository orderRepository;


    public void save(OrderCreateRequest orderCreateRequest, Integer memberCode) {

        Long totalOrderPrice = 0L;
        Long totalDeliveryPrice = 0L;

        // 여러 스토어별 주문을 리스트 처리
        List<StoreOrder> storeOrders = new ArrayList<>();
        for(OrderCreateRequest.StoreOrderRequest storeOrderRequest : orderCreateRequest.getStoreOrders()) {

            long storeCode;

            // 주문 상세를 리스트 처리(해당 스토어의 주문 상품 옵션 목록)
            List<OrderDetail> orderDetails = new ArrayList<>();
            for(OrderCreateRequest.OrderDetailRequest orderDetailRequest : storeOrderRequest.getOrderDetails()) {

                /* 재고 수정 */
                updateStock(orderDetailRequest.getOptionCode(), orderDetailRequest.getOrderQuantity());

                /* !장바구니 제거 updateCart */


                /* 옵션 가격 -> 상품 판매가 */
                ProductOption productOption = productOptionService.findProductOption(orderDetailRequest.getOptionCode());

                /* 상품 판매가 x 수량 = 주문 상세 테이블마다 총 주문금액 컬럼이 없으므로 변수로 따로 저장 */
                Long orderPrice = productOption.getOptionPrice() * orderDetailRequest.getOrderQuantity();
                totalOrderPrice += orderPrice;

                /* !쿠폰 사용시 쿠폰 발행 테이블 -> 쿠폰 정보 테이블 -> 쿠폰 적용상품 테이블
                 * 2. 할인률 * 상품 판매가 < 최대할인액 확인
                 * 3. true 주문 상세 테이블에 할인금액 컬럼에 (주문 금액 - 할인률 * 상품 판매가)를 저장
                 * 4. false (주문 금액 - 최대할인액)를 저장  */

                final OrderDetail newOrderDetail = OrderDetail.of(
                        orderDetailRequest.getOptionCode(),
                        orderDetailRequest.getOrderQuantity(),
                        productOption.getOptionPrice()
                        //할인금액
                );
                orderDetails.add(newOrderDetail);

                /* !상품 테이블에서 스토어 코드를 가져온 후 넣기 */
                storeCode = productOption.getProduct().getStoreCode();
            }


            /* !스토어 코드 가져오면서 스토어에 설정된 배송비 컬럼도 가져와서
            * 변수에 넣는다. */

            final StoreOrder newStoreOrder = StoreOrder.of(
                    // 스토어 코드,
                    orderDetails
            );
            /* !스토어 테이블(FK)의 배송비 컬럼을 불러와서
             * 주문 테이블의 총 배송비에 합산해야 한다. */

            storeOrders.add(newStoreOrder);
        }


        // 배송지 테이블에서 불러옴
        Optional<DeliveryAddress> addressOptional = deliveryAddressRepository.findById(orderCreateRequest.getDeliveryAddressCode());
        // 배송지 테이블의 값이 없다면? 등록해야지!
//        if (!addressOptional.isPresent()) {
//        }
        DeliveryAddress address = addressOptional.get();

        // 주문 테이블에 저장할 것은?
        final Order newOrder = Order.of(
                memberCode,

                address.getReceiver(),
                address.getPhone(),

                address.getAddressZipcode(),
                address.getAddressSido(),
                address.getAddressSigungu(),
                address.getAddressDongeupmyeon(),
                address.getAddressDetail(),
                address.getRequest(),

                /* 주문금액, 총할인, 배송비, 실결제금액 */
//                totalOrderPrice,
//                orderCreateRequest.getDiscountAmount(),
//                orderCreateRequest.getDeliveryAmount(),
//                orderCreateRequest.getRealPayment(),

                storeOrders
        );

        // Order 및 StoreOrder 엔티티 저장
        orderRepository.save(newOrder);
    }


    private void updateStock(Long productOptionCode, Long optionStock) {
        productOptionService.updateStock(productOptionCode, optionStock);
    }
    /* 장바구니 제거 */
}

