package yep.greenFire.greenfirebackend.seller.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yep.greenFire.greenfirebackend.seller.domain.repository.SellerRepository;
import yep.greenFire.greenfirebackend.seller.dto.response.StoreListResponse;
import yep.greenFire.greenfirebackend.seller.dto.response.StoreProfileResponse;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SellerService {

    private final SellerRepository sellerRepository;

    @Transactional(readOnly = true)
    public List<StoreListResponse> getStoreList(Long memberCode) {
        return sellerRepository.findByMemberCode(memberCode);
    }

    @Transactional(readOnly = true)
    public StoreProfileResponse getStoreProfile(Long storeCode) {
        return sellerRepository.findByStoreCode(storeCode);
    }
}