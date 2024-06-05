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
import yep.greenFire.greenfirebackend.user.seller.domain.entity.Store;
import yep.greenFire.greenfirebackend.user.seller.domain.repository.StoreRepository;

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
    /* 스토어 테이블에서 등록된 배송비,무료배송 조건을 받아온다. */
    private final StoreRepository storeRepository;

    private final OrderRepository orderRepository;


    public void save(OrderCreateRequest orderCreateRequest, Long memberCode) {

        Long totalOrderAmount = 0L;
        Long totalDeliveryAmount = 0L;

        // 여러 스토어별 주문을 리스트 처리
        List<StoreOrder> storeOrders = new ArrayList<>();
        for (OrderCreateRequest.StoreOrderRequest storeOrderRequest : orderCreateRequest.getStoreOrders()) {

            long storeCode = 0L;
            long orderAmount = 0L;
            long deliveryAmount = 0;

            // 주문 상세를 리스트 처리(해당 스토어의 주문 상품 옵션 목록)
            List<OrderDetail> orderDetails = new ArrayList<>();
            for (OrderCreateRequest.OrderDetailRequest orderDetailRequest : storeOrderRequest.getOrderDetails()) {

                /* 재고 수정 */
                updateStock(orderDetailRequest.getOptionCode(), orderDetailRequest.getOrderQuantity());

                /* !장바구니 제거 updateCart */


                /* 옵션 가격 -> 상품 판매가 */
                ProductOption productOption = productOptionService.findProductOption(orderDetailRequest.getOptionCode());

                /* 상품 판매가 x 수량 = 주문 상세 테이블마다 총 주문금액 컬럼이 없으므로 변수로 따로 저장 */
                Long optionPrice = productOption.getOptionPrice() * orderDetailRequest.getOrderQuantity();
                orderAmount += optionPrice;

                /* !쿠폰 사용시 쿠폰 발행 테이블 -> 쿠폰 정보 테이블 -> 쿠폰 적용상품 테이블
                 * 2. 할인률 * 상품 판매가 < 최대할인액 확인
                 * 3. true 주문 상세 테이블에 할인금액 컬럼에 (주문 금액 - 할인률 * 상품 판매가)를 저장
                 * 4. false (주문 금액 - 최대할인액)를 저장  */

                /* orderDetail 주문 상세 객체 생성 후 데이터 저장. */
                final OrderDetail newOrderDetail = OrderDetail.of(
                        orderDetailRequest.getOptionCode(),
                        productOption.getOptionPrice(),
                        orderDetailRequest.getOrderQuantity()
                );
                orderDetails.add(newOrderDetail);

                /* 상품 테이블에서 스토어 코드를 가져온 후 넣기 */
                storeCode = productOption.getProduct().getStoreCode();
            }

            /* 스토어 테이블에 배송비 컬럼도 가져와서 변수에 넣는다. */
            Optional<Store> optionalStore = storeRepository.findByStoreCode(storeCode);
            if (optionalStore.isPresent()) {
                Store store = optionalStore.get();
                deliveryAmount = store.getDeliveryAmount();
                if (totalOrderAmount >= store.getFreeDeliveryCondition()) {
                    deliveryAmount = 0L;
                }
            }

            /* 주문 테이블의 총 배송비에 각 판매자의 배송비를 합산해야 한다. */
            totalOrderAmount += orderAmount;
            totalDeliveryAmount += deliveryAmount;

            /*storeOrder 스토어별 주문 객체 생성 후 데이터 저장.*/
            final StoreOrder newStoreOrder = StoreOrder.of(
                    storeCode,
                    orderAmount,
                    1000,// 할인금액,
                    deliveryAmount,
                    1000,//실결제금액,
                    orderDetails
            );

            storeOrders.add(newStoreOrder);

        }

        // 배송지 테이블에서 불러옴
        Optional<DeliveryAddress> addressOptional = deliveryAddressRepository.findByDeliveryAddressCodeAndMemberCode(orderCreateRequest.getDeliveryAddressCode(), memberCode);
        DeliveryAddress address = addressOptional.orElseThrow(() -> new IllegalArgumentException("Invalid delivery address"));
//        if (!addressOptional.isPresent()) {
//        }

        /*order 주문 객체 생성 후 데이터 저장.*/
        final Order newOrder = Order.of(
                memberCode,
                "양말2개다시로직만들어랑", // 오더 네임 결제하면서 추가

                address.getReceiverName(),
                address.getContactNumber(),

                address.getAddressZonecode(),
                address.getAddressType(),
                address.getAddress(),
                address.getAddressDetail(),
                address.getDeliveryRequest(),

                /* 주문금액, 총할인, 배송비, 실결제금액 */
                totalOrderAmount,
                10000L,  //orderCreateRequest.getDiscountAmount(),
                totalDeliveryAmount,
                10000L,  //orderCreateRequest.getRealPayment(),
                storeOrders
        );
        orderRepository.save(newOrder);
    }


    private void updateStock(Long productOptionCode, Long optionStock) {
        productOptionService.updateStock(productOptionCode, optionStock);
    }
    /* 장바구니 제거 */
}