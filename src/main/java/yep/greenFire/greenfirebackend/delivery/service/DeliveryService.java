package yep.greenFire.greenfirebackend.delivery.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yep.greenFire.greenfirebackend.common.exception.NotFoundException;
import yep.greenFire.greenfirebackend.common.exception.type.ExceptionCode;
import yep.greenFire.greenfirebackend.delivery.domain.entity.DeliveryAddress;
import yep.greenFire.greenfirebackend.delivery.domain.repository.DeliveryAddressRepository;
import yep.greenFire.greenfirebackend.delivery.domain.type.AddressType;
import yep.greenFire.greenfirebackend.delivery.dto.request.DeliveryAddressRequest;
import yep.greenFire.greenfirebackend.delivery.dto.response.DeliveryAddressResponse;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class DeliveryService {

    private final DeliveryAddressRepository deliveryAddressRepository;

    // 배송지 등록
    public void save(DeliveryAddressRequest deliveryRequest, Long memberCode) {

        // 회원의 기존 배송지 목록을 조회
        List<DeliveryAddress> deliveryAddresses = Optional.ofNullable(deliveryAddressRepository.findByMemberCode(memberCode))
                                                            .orElse(Collections.emptyList());

        // 만약 회원이 배송지를 처음 등록하는거라면 기본 배송지로 등록한다.(getIsOrdinaryAddress == true)
        // 다수의 배송지를 가지고 있다면 그 중에 하나만 기본 배송지로 설정할 수 있다.(getIsOrdinaryAddress == true)는 하나만!

        Boolean isOrdinaryAddress;

        if (deliveryAddresses.isEmpty()) {
            // 처음 등록하는거라면 기본 배송지로
            isOrdinaryAddress = true;
        } else if (!deliveryRequest.getIsOrdinaryAddress()) {
            // 다수의 배송지를 가지고 기본 배송지로 설정하지 않으려는 경우
            isOrdinaryAddress = false;
        } else {
            // 다수의 배송지를 가지고 기본 배송지로 설정하려는 경우 기존 기본 배송지 해제
            isOrdinaryAddress = true;
            deliveryAddresses.stream()
                    .filter(DeliveryAddress::getIsOrdinaryAddress)
                    .forEach(DeliveryAddress::deactivateOrdinaryAddress);
            deliveryAddressRepository.saveAll(deliveryAddresses);
        }

        AddressType addressType = AddressType.fromValue(deliveryRequest.getAddressType());

        final DeliveryAddress newDeliveryAddress = DeliveryAddress.of(
                memberCode,

                deliveryRequest.getDeliveryAddressName(),

                isOrdinaryAddress,

                deliveryRequest.getReceiverName(),
                deliveryRequest.getContactNumber(),

                deliveryRequest.getAddressZonecode(),
                addressType,
                deliveryRequest.getAddress(),
                deliveryRequest.getAddressDetail(),
                deliveryRequest.getDeliveryRequest()
        );
        deliveryAddressRepository.save(newDeliveryAddress);
    }

    // 배송지 조회
    public List<DeliveryAddressResponse> getDeliveryAddress(Long memberCode) {

        return deliveryAddressRepository.findByMemberCode(memberCode).stream()
                .map(deliveryAddress -> new DeliveryAddressResponse(deliveryAddress))
                .collect(Collectors.toList());
    }

    // 배송지 삭제
    public void deleteDeliveryAddress(Long deliveryAddressCode, Long memberCode) {
        DeliveryAddress deliveryAddress = deliveryAddressRepository.findByDeliveryAddressCodeAndMemberCode(deliveryAddressCode, memberCode)
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_VALID_DELIVERY));
        deliveryAddressRepository.delete(deliveryAddress);
    }
}

