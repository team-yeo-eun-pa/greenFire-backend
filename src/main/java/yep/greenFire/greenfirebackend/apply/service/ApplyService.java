package yep.greenFire.greenfirebackend.apply.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yep.greenFire.greenfirebackend.apply.domain.repository.ApplyRepository;
import yep.greenFire.greenfirebackend.apply.dto.response.ApplyResponse;
import yep.greenFire.greenfirebackend.seller.domain.entity.Seller;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ApplyService {

    private final ApplyRepository applyRepository;

    private Pageable getPageable(Integer page) {
        return PageRequest.of(page - 1, 10, Sort.by("sellerCode").descending());
    }

    @Transactional(readOnly = true)
    public Page<ApplyResponse> getApplies(Integer page, Long memberCode) {

        return applyRepository.findByMemberCode(getPageable(page), memberCode);
    }
}
