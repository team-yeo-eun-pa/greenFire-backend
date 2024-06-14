package yep.greenFire.greenfirebackend.delivery.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yep.greenFire.greenfirebackend.delivery.domain.entity.Delivery;
import yep.greenFire.greenfirebackend.delivery.domain.repository.DeliveryRepository;
import yep.greenFire.greenfirebackend.delivery.domain.type.DeliveryType;
import yep.greenFire.greenfirebackend.delivery.dto.request.DeliveryRequest;

@Service
@RequiredArgsConstructor
@Transactional
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    public void save(DeliveryRequest deliveryRequest) {

        DeliveryType deliveryType = deliveryRequest.getDeliveryType();

        final Delivery newDelivery = Delivery.of(
                deliveryRequest.getStoreOrderCode(),
                deliveryRequest.getDeliveryCompany(),
                deliveryRequest.getTransportNumber(),
                deliveryType
        );
        deliveryRepository.save(newDelivery);
    }
}
