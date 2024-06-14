//package yep.greenFire.greenfirebackend.order.service;
//
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Service;
//import yep.greenFire.greenfirebackend.common.exception.NotFoundException;
//import yep.greenFire.greenfirebackend.common.exception.type.ExceptionCode;
//import yep.greenFire.greenfirebackend.delivery.domain.entity.DeliveryAddress;
//import yep.greenFire.greenfirebackend.delivery.domain.repository.DeliveryAddressRepository;
//import yep.greenFire.greenfirebackend.delivery.domain.repository.DeliveryRepository;
//import yep.greenFire.greenfirebackend.order.domain.entity.Order;
//import yep.greenFire.greenfirebackend.order.domain.entity.OrderDetail;
//import yep.greenFire.greenfirebackend.order.domain.entity.StoreOrder;
//import yep.greenFire.greenfirebackend.order.domain.repository.OrderDetailRepository;
//import yep.greenFire.greenfirebackend.order.domain.repository.OrderRepository;
//import yep.greenFire.greenfirebackend.order.domain.repository.StoreOrderRepository;
//import yep.greenFire.greenfirebackend.order.domain.type.OrderStatus;
//import yep.greenFire.greenfirebackend.order.dto.request.OrderCreateRequest;
//import yep.greenFire.greenfirebackend.order.dto.response.OrderDetailDTO;
//import yep.greenFire.greenfirebackend.order.dto.response.OrderResponse;
//import yep.greenFire.greenfirebackend.product.domain.entity.ProductOption;
//import yep.greenFire.greenfirebackend.product.service.ProductOptionService;
//import yep.greenFire.greenfirebackend.store.domain.entity.Store;
//import yep.greenFire.greenfirebackend.store.domain.repository.StoreRepository;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//@Transactional
//public class OrderService {
//
//    /* 재고 수량 테이블에서 재고를 조절한다. */
//    private final ProductOptionService productOptionService;
//    /* 배송지 테이블에서 등록된 배송지를 받아온다. */
//    private final DeliveryAddressRepository deliveryAddressRepository;
//    /* 스토어 테이블에서 등록된 배송비,무료배송 조건을 받아온다. */
//    private final StoreRepository storeRepository;
//
//    private final OrderRepository orderRepository;
//
//    private final DeliveryRepository deliveryRepository;
//
//    private final StoreOrderRepository storeOrderRepository;
//
//    private final OrderDetailRepository orderDetailRepository;
//
//
//    // 주문 등록
//    public void save(OrderCreateRequest orderCreateRequest, Long memberCode) {
//
//        Long totalOrderAmount = 0L;
//        Long totalDeliveryAmount = 0L;
//
//        // 여러 스토어별 주문을 리스트 처리
//        List<StoreOrder> storeOrders = new ArrayList<>();
//        for (OrderCreateRequest.StoreOrderRequest storeOrderRequest : orderCreateRequest.getStoreOrders()) {
//
//            long storeCode = 0L;
//            long orderAmount = 0L;
//            long deliveryAmount = 0;
//
//            // 주문 상세를 리스트 처리(해당 스토어의 주문 상품 옵션 목록)
//            List<OrderDetail> orderDetails = new ArrayList<>();
//            for (OrderCreateRequest.OrderDetailRequest orderDetailRequest : storeOrderRequest.getOrderDetails()) {
//
//                /* 재고 수정 */
//                updateStock(orderDetailRequest.getOptionCode(), orderDetailRequest.getOrderQuantity());
//
//                /* !장바구니 제거 updateCart */
//
//
//                /* 옵션 가격 -> 상품 판매가 */
//                ProductOption productOption = productOptionService.findProductOption(orderDetailRequest.getOptionCode());
//
//                /* 상품 판매가 x 수량 = 주문 상세 테이블마다 총 주문금액 컬럼이 없으므로 변수로 따로 저장 */
//                Long optionPrice = productOption.getOptionPrice() * orderDetailRequest.getOrderQuantity();
//                orderAmount += optionPrice;
//
//                /* !쿠폰 사용시 쿠폰 발행 테이블 -> 쿠폰 정보 테이블 -> 쿠폰 적용상품 테이블
//                 * 2. 할인률 * 상품 판매가 < 최대할인액 확인
//                 * 3. true 주문 상세 테이블에 할인금액 컬럼에 (주문 금액 - 할인률 * 상품 판매가)를 저장
//                 * 4. false (주문 금액 - 최대할인액)를 저장  */
//
//                /* orderDetail 주문 상세 객체 생성 후 데이터 저장. */
//                final OrderDetail newOrderDetail = OrderDetail.of(
//                        orderDetailRequest.getOptionCode(),
//                        productOption.getOptionPrice(),
//                        orderDetailRequest.getOrderQuantity()
//                );
//                orderDetails.add(newOrderDetail);
//
//                /* 상품 테이블에서 스토어 코드를 가져온 후 넣기 */
////                storeCode = Product.getProduct().getStoreCode();
//                storeCode = 1;
//            }
//
//            /* 스토어 테이블에 배송비 컬럼도 가져와서 변수에 넣는다. */
//            Optional<Store> optionalStore = storeRepository.findByStoreCode(storeCode);
//            if (optionalStore.isPresent()) {
//                Store store = optionalStore.get();
//                deliveryAmount = store.getDeliveryAmount();
//                if (totalOrderAmount >= store.getFreeDeliveryCondition()) {
//                    deliveryAmount = 0L;
//                }
//            }
//
//            /* 주문 테이블의 총 배송비에 각 판매자의 배송비를 합산해야 한다. */
//            totalOrderAmount += orderAmount;
//            totalDeliveryAmount += deliveryAmount;
//
//            /*storeOrder 스토어별 주문 객체 생성 후 데이터 저장.*/
//            final StoreOrder newStoreOrder = StoreOrder.of(
//                    storeCode,
//                    orderAmount,
//                    1000,// 할인금액,
//                    deliveryAmount,
//                    1000,//실결제금액,
//                    orderDetails
//            );
//
//            storeOrders.add(newStoreOrder);
//
//        }
//
//        // 배송지 테이블에서 불러옴
//        Optional<DeliveryAddress> addressOptional = deliveryAddressRepository.findByDeliveryAddressCodeAndMemberCode(orderCreateRequest.getDeliveryAddressCode(), memberCode);
//        DeliveryAddress address = addressOptional.orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_DELIVERY_CODE));
////        if (!addressOptional.isPresent()) {
////        }
//
//        /*order 주문 객체 생성 후 데이터 저장.*/
//        final Order newOrder = Order.of(
//                memberCode,
//                "양말2개다시로직만들어랑", // 오더 네임 결제하면서 추가
//
//                address.getReceiverName(),
//                address.getContactNumber(),
//
//                "우편번호 다시 넣어라",
//                address.getAddressType(),
//                address.getAddress(),
//                address.getAddressDetail(),
//                address.getDeliveryRequest(),
//
//                /* 주문금액, 총할인, 배송비, 실결제금액 */
//                totalOrderAmount,
//                10000L,  //orderCreateRequest.getDiscountAmount(),
//                totalDeliveryAmount,
//                10000L,  //orderCreateRequest.getRealPayment(),
//                storeOrders
//        );
//        orderRepository.save(newOrder);
//    }
//
//
//    /* 주문시 상품 재고 수량 */
//    private void updateStock(Long productOptionCode, Long optionStock) {
//        productOptionService.updateStock(productOptionCode, optionStock);
//    }
//
//    /* 주문시 장바구니에서 상품 제거 */
//
//
//    private Pageable getPageable(Integer page) {
//        return PageRequest.of(page - 1, 10, Sort.by("orderCode").descending());
//    }
//
//    // 회원 - 주문 조회
//    @Transactional
//    public Page<OrderResponse> getOrderByMemberCode(Long memberCode, Integer page) {
//
//        Page<OrderResponse> orderResponses = orderRepository.findByMemberCode(memberCode, getPageable(page));
//
//        orderResponses.forEach(orderResponse -> {
//            orderResponse.getStoreOrders().forEach(storeOrderDTO -> {
//                List<OrderDetailDTO> orderDetailDTOs = orderDetailRepository.findByStoreOrderCode(storeOrderDTO.getStoreOrderCode());
//
//
//                storeOrderDTO.setOrderDetails(orderDetailDTOs);
//            });
//        });
//        return orderResponses;
//    }
//
//    // 스토어 - 주문 조회
//    @Transactional
//    public Page<OrderResponse> getOrderByStoreCode(Long storeCode, Integer page) {
//
//        Page<OrderResponse> orderResponses = storeOrderRepository.findByStoreCode(storeCode, getPageable(page));
//
//        orderResponses.forEach(orderResponse -> {
//            orderResponse.getStoreOrders().forEach(storeOrderDTO -> {
//                List<OrderDetailDTO> orderDetailDTOs = orderDetailRepository.findByStoreOrderCode(storeOrderDTO.getStoreOrderCode());
//
//
//                storeOrderDTO.setOrderDetails(orderDetailDTOs);
//            });
//        });
//        return orderResponses;
//    }
//
//    // 스토어 & 주문 상태에 따른 - 주문 조회
//    @Transactional
//    public Page<OrderResponse> getStoreOrderStatus(Long storeCode, List<String> storeOrderStatus, Integer page) {
//
//
////        OrderStatus orderStatus = OrderStatus.fromValue(storeOrderStatus);
//        List<OrderStatus> orderStatuses = storeOrderStatus.stream()
//                .map(OrderStatus::fromValue)
//                .collect(Collectors.toList());
//
////        return orderRepository.findByStoreCodeAndOrderStatus(storeCode, orderStatuses, getPageable(page));
//        return null;
//
//    }
//
//
////    // 스토어 - 주문 상태 변경
////    public void modifyOrderStatus(OrderApprovalRequest orderApprovalRequest) {
////        Optional<Order> orderOptional = orderRepository.findByOrderCode(orderApprovalRequest.getOrderCode());
////
////        if (orderOptional.isPresent()) {
////            List<StoreOrder> storeOrders = orderOptional.get().getStoreOrders();
////
////            for (StoreOrder storeOrder : storeOrders) {
////
////                if (storeOrder.getStoreOrderCode().equals(orderApprovalRequest.getStoreOrderCode())) {
////
////                    // orderStatus가 RECEIVED에서 REJECTED 또는 PROCESSING으로 바뀔 수 있다.
////
////                    OrderStatus orderStatus = OrderStatus.fromValue(orderApprovalRequest.getOrderStatus());
////
////                    if (orderStatus == OrderStatus.REJECTED || orderStatus == OrderStatus.PROCESSING) {
////
////                        storeOrder.modifyOrderApply(
////                                orderStatus,
////                                LocalDateTime.now(),
////                                orderApprovalRequest.getRejectionReason());
////
////                    }
////                    System.out.println("storeorder : " + storeOrder);
////
////                    // orderStatus가 PROCESSING에서 SHIPPED으로 바뀌면 운송장 정보도 테이블에 등록할 수 있게 해야한다.
////
////                    if (orderStatus == OrderStatus.SHIPPED && storeOrder.getRejectionReason().isEmpty()) {
////
////                        DeliveryType deliveryType = DeliveryType.fromValue(orderApprovalRequest.getDeliveryType());
////
////                        final Delivery newDelivery = Delivery.of(
////                                orderApprovalRequest.getStoreOrderCode(),
////                                orderApprovalRequest.getDeliveryCompany(),
////                                orderApprovalRequest.getTransportNumber(),
////                                deliveryType
////                        );
////
////                        deliveryRepository.save(newDelivery);
////
////                    } else {
////                        throw new NotFoundException(ExceptionCode.ORDER_ALREADY_REJECTED);
////                    }
////                }
////            }
////        } else {
////            throw new NotFoundException(ExceptionCode.NOT_FOUND_VALID_ORDER);
////        }
////    }
////
////    public Page<OrderResponse> getOrders(Long memberCode, Integer page) {
////    }
//}