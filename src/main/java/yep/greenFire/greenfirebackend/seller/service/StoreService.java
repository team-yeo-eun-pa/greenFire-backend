package yep.greenFire.greenfirebackend.seller.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yep.greenFire.greenfirebackend.store.domain.repository.StoreRepository;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

}

