package yep.greenFire.greenfirebackend.order.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import yep.greenFire.greenfirebackend.common.exception.NotFoundException;
import yep.greenFire.greenfirebackend.common.exception.type.ExceptionCode;
import yep.greenFire.greenfirebackend.delivery.domain.entity.DeliveryAddress;
import yep.greenFire.greenfirebackend.delivery.domain.repository.DeliveryAddressRepository;
import yep.greenFire.greenfirebackend.delivery.dto.request.DeliveryRequest;
import yep.greenFire.greenfirebackend.delivery.service.DeliveryService;
import yep.greenFire.greenfirebackend.order.domain.entity.Order;
import yep.greenFire.greenfirebackend.order.domain.entity.OrderDetail;
import yep.greenFire.greenfirebackend.order.domain.entity.StoreOrder;
import yep.greenFire.greenfirebackend.order.domain.repository.OrderDetailRepository;
import yep.greenFire.greenfirebackend.order.domain.repository.OrderRepository;
import yep.greenFire.greenfirebackend.order.domain.repository.StoreOrderRepository;
import yep.greenFire.greenfirebackend.order.domain.type.OrderStatus;
import yep.greenFire.greenfirebackend.order.dto.request.OrderApprovalRequest;
import yep.greenFire.greenfirebackend.order.dto.request.OrderCreateRequest;
import yep.greenFire.greenfirebackend.order.dto.response.OrderDetailDTO;
import yep.greenFire.greenfirebackend.order.dto.response.OrderResponse;
import yep.greenFire.greenfirebackend.product.domain.entity.Product;
import yep.greenFire.greenfirebackend.product.domain.entity.ProductOption;
import yep.greenFire.greenfirebackend.product.domain.repository.ProductOptionRepository;
import yep.greenFire.greenfirebackend.product.domain.repository.ProductRepository;
import yep.greenFire.greenfirebackend.product.service.ProductOptionService;
import yep.greenFire.greenfirebackend.store.domain.entity.Store;
import yep.greenFire.greenfirebackend.store.domain.repository.StoreRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final StoreOrderRepository storeOrderRepository;
    private final OrderDetailRepository orderDetailRepository;

    // 주문 등록 - 배송비 조회
    private final StoreRepository storeRepository;
    // 주문 등록 - 배송지 조회
    private final DeliveryAddressRepository deliveryAddressRepository;
    // 주문 등록 - 주문 이름, 가격
    private final ProductOptionRepository productOptionRepository;
    private final ProductRepository productRepository;
    // 주문 등록 - 재고 수량 업데이트
    private final ProductOptionService productOptionService;
    // 주문 수정 - 운송장 등록
    private final DeliveryService deliveryService;


    // 주문 등록
    @Transactional
    public Order save(OrderCreateRequest orderCreateRequest, Long memberCode) {

        Long totalOrderAmount = 0L;
        Long totalDeliveryAmount = 0L;

        List<StoreOrder> storeOrders = new ArrayList<>();

        for (OrderCreateRequest.StoreOrderRequest storeOrderRequest : orderCreateRequest.getStoreOrders()) {

            StoreOrderData storeOrderData = processStoreOrder(storeOrderRequest);
            totalOrderAmount += storeOrderData.getOrderAmount();
            totalDeliveryAmount += storeOrderData.getDeliveryAmount();
            storeOrders.add(storeOrderData.getStoreOrder());

        }

        // 배송지 정보 조회
        DeliveryAddress address = getDeliveryAddress(orderCreateRequest.getDeliveryAddressCode(), memberCode);

        // orderName 생성 (예: "두부과자 외 4건")
        String orderName = generateOrderName(storeOrders);

        // 주문 객체 생성 후 데이터 저장
        final Order newOrder = createOrder(memberCode, orderName, totalOrderAmount, totalDeliveryAmount, storeOrders, address);
        orderRepository.save(newOrder);

        return newOrder; // 새로운 주문 객체 반환
    }

    // 스토어별 주문
    private StoreOrderData processStoreOrder(OrderCreateRequest.StoreOrderRequest storeOrderRequest) {
        Long orderAmount = 0L;
        Long storeCode = storeOrderRequest.getStoreCode();

        // 주문 상세를 리스트로 처리
        List<OrderDetail> orderDetails = new ArrayList<>();
        for (OrderCreateRequest.OrderDetailRequest orderDetailRequest : storeOrderRequest.getOrderDetails()) {

            // 재고 수량 업데이트
            updateStock(orderDetailRequest.getOptionCode(), orderDetailRequest.getOrderQuantity());

            // 상품 옵션 정보 조회
            ProductOption productOption = productOptionService.findProductOption(orderDetailRequest.getOptionCode());
            Long optionPrice = productOption.getOptionPrice();
            Long totalOptionPrice = optionPrice * orderDetailRequest.getOrderQuantity();

            // 총 옵션 가격 계산
            orderAmount += totalOptionPrice;

            // 주문 상세 객체 생성 후 리스트에 추가
            final OrderDetail newOrderDetail = OrderDetail.of(
                    orderDetailRequest.getOptionCode(),
                    optionPrice,
                    orderDetailRequest.getOrderQuantity()
            );
            orderDetails.add(newOrderDetail);
        }

        // 배송비 계산
        Long deliveryAmount = calculateDeliveryAmount(storeCode, orderAmount);

        // 스토어 주문 객체 생성 후 데이터 저장
        final StoreOrder newStoreOrder = StoreOrder.of(
                storeCode,
                orderAmount,
                0,
                deliveryAmount,
                orderAmount + deliveryAmount,
                orderDetails
        );

        return new StoreOrderData(newStoreOrder, orderAmount, deliveryAmount);
    }

    // 배송비 계산
    private Long calculateDeliveryAmount(Long storeCode, Long orderAmount) {
        Optional<Store> optionalStore = storeRepository.findByStoreCode(storeCode);
        if (optionalStore.isPresent()) {
            Store store = optionalStore.get();
            Long deliveryAmount = store.getDeliveryAmount();
            if (orderAmount >= store.getFreeDeliveryCondition()) {
                return 0L;
            }
            return deliveryAmount;
        }
        throw new NotFoundException(ExceptionCode.NOT_FOUND_SELLERS_STORE_CODE);
    }

    // 배송지 조회
    private DeliveryAddress getDeliveryAddress(Long deliveryAddressCode, Long memberCode) {
        return deliveryAddressRepository.findByDeliveryAddressCodeAndMemberCode(deliveryAddressCode, memberCode)
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_DELIVERY_CODE));
    }

    // 주문 객체 생성
    private Order createOrder(Long memberCode, String orderName, Long totalOrderAmount, Long totalDeliveryAmount, List<StoreOrder> storeOrders, DeliveryAddress address) {
        return Order.of(
                memberCode,
                orderName,
                address.getReceiverName(),
                address.getContactNumber(),
                address.getAddressZonecode(),
                address.getAddressType(),
                address.getAddress(),
                address.getAddressDetail(),
                address.getDeliveryRequest(),
                totalOrderAmount,
                0L,
                totalDeliveryAmount,
                totalOrderAmount + totalDeliveryAmount,
                storeOrders
        );
    }


    // 제고 수량 업데이트
    private void updateStock(Long productOptionCode, Long optionStock) {
        productOptionService.updateStock(productOptionCode, optionStock);
    }

    private Pageable getPageable(Integer page) {
        return PageRequest.of(page - 1, 10, Sort.by("orderCode").descending());
    }

    // 주문명 생성
    private String generateOrderName(List<StoreOrder> storeOrders) {
        if (storeOrders.isEmpty()) {
            return "";
        }

        // 전체 아이템 수 계산
        int totalItemCount = storeOrders.stream()
                .mapToInt(storeOrder -> storeOrder.getOrderDetails().size())
                .sum();

        // 첫 번째 아이템의 이름 가져오기
        String firstItemName = storeOrders.stream()
                .flatMap(storeOrder -> storeOrder.getOrderDetails().stream())
                .map(orderDetail -> {
                    ProductOption productOption = productOptionRepository.findById(orderDetail.getOptionCode())
                            .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_PRODUCT_CODE));
                    Product product = productRepository.findById(productOption.getProductCode())
                            .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_PRODUCT_CODE));
                    return product.getProductName();
                })
                .findFirst()
                .orElse("");

        return firstItemName + " 외 " + (totalItemCount - 1) + "건";
    }

    // 스토어 주문 데이터 클래스
    private static class StoreOrderData {
        private final StoreOrder storeOrder;
        private final Long orderAmount;
        private final Long deliveryAmount;

        public StoreOrderData(StoreOrder storeOrder, Long orderAmount, Long deliveryAmount) {
            this.storeOrder = storeOrder;
            this.orderAmount = orderAmount;
            this.deliveryAmount = deliveryAmount;
        }

        public StoreOrder getStoreOrder() {
            return storeOrder;
        }

        public Long getOrderAmount() {
            return orderAmount;
        }

        public Long getDeliveryAmount() {
            return deliveryAmount;
        }
    }

    //-------------------------------------------------------------------------------------------------------

    // 주문 상태 수정 - 스토어 주문 승인/거절
    public void modifyOrderStatus(OrderApprovalRequest orderApprovalRequest) {

        Optional<Order> orderOptional = orderRepository.findByOrderCode(orderApprovalRequest.getOrderCode());

        if (orderOptional.isPresent()) {
            List<StoreOrder> storeOrders = orderOptional.get().getStoreOrders();

            for (StoreOrder storeOrder : storeOrders) {

                if (storeOrder.getStoreOrderCode().equals(orderApprovalRequest.getStoreOrderCode())) {

                    OrderStatus orderStatus = orderApprovalRequest.getOrderStatus();

                    if (orderStatus == OrderStatus.REJECTED || orderStatus == OrderStatus.PROCESSING) {

                        storeOrder.modifyStatusApply(
                                orderStatus,
                                LocalDateTime.now(),
                                orderApprovalRequest.getRejectionReason());

                        // 주문 거절 상태일 때 OrderDetail의 isOrderCancel을 true로 설정
                        if (orderStatus == OrderStatus.REJECTED) {
                            List<OrderDetail> orderDetails = storeOrder.getOrderDetails();
                            for (OrderDetail orderDetail : orderDetails) {
                                orderDetail.setOrderCancel(true);
                            }
                        }
                    }
                }
            }
        } else {
            throw new NotFoundException(ExceptionCode.NOT_FOUND_VALID_ORDER);
        }
    }

    //  주문 상태 수정 (판매자 배송처리)
    public void modifyOrderStatusAndRegistDelivery(DeliveryRequest deliveryRequest) {

        StoreOrder storeOrder = storeOrderRepository.findByStoreOrderCodeAndOrderStatus(deliveryRequest.getStoreOrderCode(), OrderStatus.PROCESSING)
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_VALID_ORDER));

        storeOrder.modifyStoreOrderStatus(OrderStatus.SHIPPED);
        deliveryService.save(deliveryRequest);
    }

    //-------------------------------------------------------------------------------------------------------

    // memberCode 기준 조회
    @Transactional
    public Page<OrderResponse> getOrderByMemberCode(Long memberCode, Integer page) {

        Page<OrderResponse> orderResponses = orderRepository.findByMemberCode(memberCode, getPageable(page));

        orderResponses.forEach(orderResponse -> {
            orderResponse.getStoreOrders().forEach(storeOrderDTO -> {
                List<OrderDetailDTO> orderDetailDTOs = orderDetailRepository.findByStoreOrderCode(storeOrderDTO.getStoreOrderCode());

                storeOrderDTO.setOrderDetails(orderDetailDTOs);
            });
        });
        return orderResponses;
    }

    // orderCode 기준 조회
    @Transactional
    public OrderResponse getOrderByOrderCode(Long orderCode) {
        OrderResponse orderResponse = orderRepository.findOrderResponseByOrderCode(orderCode)
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_VALID_ORDER));

        orderResponse.getStoreOrders().forEach(storeOrderDTO -> {
            List<OrderDetailDTO> orderDetailDTOs = orderDetailRepository.findByStoreOrderCode(storeOrderDTO.getStoreOrderCode());
            storeOrderDTO.setOrderDetails(orderDetailDTOs);
        });
        return orderResponse;
    }

    // storeCode 기준 조회
    @Transactional
    public Page<OrderResponse> getOrderByStoreCode(Long storeCode, Integer page) {

        Page<OrderResponse> orderResponses = storeOrderRepository.findByStoreCode(storeCode, getPageable(page));

        orderResponses.forEach(orderResponse -> {
            orderResponse.getStoreOrders().forEach(storeOrderDTO -> {
                List<OrderDetailDTO> orderDetailDTOs = orderDetailRepository.findByStoreOrderCode(storeOrderDTO.getStoreOrderCode());

                storeOrderDTO.setOrderDetails(orderDetailDTOs);
            });
        });
        return orderResponses;
    }

    // storeCode & orderStatus 기준 조회
    @Transactional
    public Page<OrderResponse> getOrderByStoreCodeAndOrderStatus(Long storeCode, List<String> storeOrderStatus, Integer page) {
        List<OrderStatus> orderStatuses = storeOrderStatus.stream()
                .map(OrderStatus::fromValue)
                .collect(Collectors.toList());

        Page<OrderResponse> orderResponses = storeOrderRepository.findByStoreCodeAndOrderStatus(storeCode, orderStatuses, getPageable(page));

        orderResponses.forEach(orderResponse -> {
            orderResponse.getStoreOrders().forEach(storeOrderDTO -> {
                List<OrderDetailDTO> orderDetailDTOs = orderDetailRepository.findByStoreOrderCode(storeOrderDTO.getStoreOrderCode());
                storeOrderDTO.setOrderDetails(orderDetailDTOs);
            });
        });
        return orderResponses;
    }
}