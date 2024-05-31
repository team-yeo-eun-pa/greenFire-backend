package yep.greenFire.greenfirebackend.user.seller.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yep.greenFire.greenfirebackend.user.seller.domain.entity.Store;
import yep.greenFire.greenfirebackend.user.seller.domain.repository.StoreRepository;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

}

