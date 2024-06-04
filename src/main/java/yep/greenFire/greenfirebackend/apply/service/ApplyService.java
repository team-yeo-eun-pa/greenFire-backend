package yep.greenFire.greenfirebackend.apply.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import yep.greenFire.greenfirebackend.apply.domain.repository.ApplyRepository;
import yep.greenFire.greenfirebackend.apply.dto.request.ApplyCreateRequest;
import yep.greenFire.greenfirebackend.apply.dto.response.ApplyResponse;
import yep.greenFire.greenfirebackend.common.util.FileUploadUtils;
import yep.greenFire.greenfirebackend.seller.domain.entity.Seller;

import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ApplyService {

    private final ApplyRepository applyRepository;

    @Value("http://localhost:8001/storeimgs")
    private String IMAGE_URL;
    @Value("src/main/resources/static/storeimgs")
    private String IMAGE_DIR;

    private Pageable getPageable(Integer page) {
        return PageRequest.of(page - 1, 10, Sort.by("sellerCode").descending());
    }

    @Transactional(readOnly = true)
    public Page<ApplyResponse> getApplies(Integer page, Long memberCode) {

        return applyRepository.findByMemberCode(getPageable(page), memberCode);
    }

    private String getRandomName() { return UUID.randomUUID().toString().replace("-", ""); }

    public Long save(final ApplyCreateRequest applyCreateRequest, final MultipartFile businessImg) {

        String replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, getRandomName(), businessImg);

        final Seller newSeller = Seller.of(
                applyCreateRequest.getStoreName(),
                applyCreateRequest.getBusinessNumber(),
                applyCreateRequest.getMosNumber(),
                applyCreateRequest.getStoreType(),
                applyCreateRequest.getMemberPhone(),
                applyCreateRequest.getApplyContent(),
                IMAGE_URL + replaceFileName
        );

        final Seller seller = applyRepository.save(newSeller);

        return seller.getSellerCode();
    }
}
