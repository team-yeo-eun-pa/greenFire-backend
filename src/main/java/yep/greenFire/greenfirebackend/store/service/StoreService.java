package yep.greenFire.greenfirebackend.store.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yep.greenFire.greenfirebackend.apply.dto.request.ApplyUpdateRequest;
import yep.greenFire.greenfirebackend.common.exception.NotFoundException;
import yep.greenFire.greenfirebackend.common.exception.type.ExceptionCode;
import yep.greenFire.greenfirebackend.order.domain.entity.OrderDetail;
import yep.greenFire.greenfirebackend.order.domain.repository.OrderRepository;
import yep.greenFire.greenfirebackend.order.domain.type.OrderStatus;
import yep.greenFire.greenfirebackend.seller.domain.entity.Seller;
import yep.greenFire.greenfirebackend.seller.domain.repository.SellerRepository;
import yep.greenFire.greenfirebackend.store.domain.entity.Store;
import yep.greenFire.greenfirebackend.store.domain.repository.StoreRepository;
import yep.greenFire.greenfirebackend.store.domain.type.StoreStatus;
import yep.greenFire.greenfirebackend.store.dto.request.StoreCloseRequest;
import yep.greenFire.greenfirebackend.store.dto.request.StoreProfileUpdateRequest;
import yep.greenFire.greenfirebackend.store.dto.response.StoreListResponse;
import yep.greenFire.greenfirebackend.store.dto.response.StoreProfileResponse;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final SellerRepository sellerRepository;

    @Transactional
    public void createNewStore(Long sellerCode, ApplyUpdateRequest applyRequest) {

        final Store newStore = Store.of(
            sellerCode,
            applyRequest.getStoreName(),
            StoreStatus.PRE_OPEN
        );

        storeRepository.save(newStore);
    }

    @Transactional
    public void modifyNewStore(Long sellerCode, StoreProfileUpdateRequest profileRequest, Long memberCode) {
        Store store = storeRepository.findStoreForPreOpenUpdateBySellerCodeAndStoreStatus(sellerCode, StoreStatus.PRE_OPEN)
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_SELLERS_STORE_CODE));

        store.modifyProfile(
                sellerCode,
                profileRequest.getStoreName(),
                profileRequest.getStoreInfo(),
                profileRequest.getAddressZonecode(),
                profileRequest.getAddressType(),
                profileRequest.getAddress(),
                profileRequest.getAddressDetail(),
                profileRequest.getDeliveryAmount(),
                profileRequest.getFreeDeliveryCondition()
        );

        // 상태를 OPEN으로 변경하여 활성화
        store.setStoreStatus(StoreStatus.OPEN);
    }

    @Transactional(readOnly = true)
    public List<StoreListResponse> getStoreList(Long memberCode) {
        return storeRepository.findByMemberCode(memberCode);
    }

    @Transactional(readOnly = true)
    public StoreProfileResponse getStoreProfile(Long sellerCode) {
        return storeRepository.findBySellerCode(sellerCode)
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_SELLERS_STORE_CODE));
    }

    @Transactional
    public void modifyProfile(Long sellerCode, StoreProfileUpdateRequest profileRequest, Long memberCode) {

        Store store = storeRepository.findStoreForOpenUpdateBySellerCodeAndStoreStatus(sellerCode, StoreStatus.OPEN)
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_SELLERS_STORE_CODE));

        Seller seller = sellerRepository.findById(store.getSellerCode())
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_SELLERS_STORE_CODE));

        if (!seller.getMemberCode().equals(memberCode)) {
            throw new NotFoundException(ExceptionCode.ACCESS_DENIED);
        }

        store.modifyProfile(
                sellerCode,
                profileRequest.getStoreName(),
                profileRequest.getStoreInfo(),
                profileRequest.getAddressZonecode(),
                profileRequest.getAddressType(),
                profileRequest.getAddress(),
                profileRequest.getAddressDetail(),
                profileRequest.getDeliveryAmount(),
                profileRequest.getFreeDeliveryCondition()
        );
    }

    @Transactional
    public void pauseStore(Long sellerCode, StoreCloseRequest closeRequest, Long memberCode) {

        Store store = storeRepository.findStoreForOpenUpdateBySellerCodeAndStoreStatus(sellerCode, StoreStatus.OPEN)
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_SELLERS_STORE_CODE));

        // 정지할 수 없는 상태의 주문 건 확인
//        List<OrderDetail> activeOrders = orderRepository.findActiveOrders(sellerCode,
//                List.of(OrderStatus.RECEIVED, OrderStatus.PROCESSING, OrderStatus.SHIPPED));
//
//        if (!activeOrders.isEmpty()) {
//            throw new IllegalStateException("정지할 수 없는 상태의 주문 건이 존재합니다.");
//        }

        // 스토어 정지
        store.closeStore(
                sellerCode,
                closeRequest.getSuspendedEndDate()
        );

    }
}

