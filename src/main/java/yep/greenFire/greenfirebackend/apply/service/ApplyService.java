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
import yep.greenFire.greenfirebackend.apply.domain.type.ApplyStatus;
import yep.greenFire.greenfirebackend.apply.dto.request.ApplyCreateRequest;
import yep.greenFire.greenfirebackend.apply.dto.request.ApplyUpdateRequest;
import yep.greenFire.greenfirebackend.apply.dto.response.AdminApplyResponse;
import yep.greenFire.greenfirebackend.apply.dto.response.ApplyResponse;
import yep.greenFire.greenfirebackend.common.exception.NotFoundException;
import yep.greenFire.greenfirebackend.common.exception.type.ExceptionCode;
import yep.greenFire.greenfirebackend.common.util.FileUploadUtils;
import yep.greenFire.greenfirebackend.seller.domain.entity.Seller;

import java.util.UUID;

import static yep.greenFire.greenfirebackend.apply.domain.type.ApplyStatus.CANCEL;

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

    public Long save(final ApplyCreateRequest applyCreateRequest, final MultipartFile businessImg, Long memberCode) {

        String replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, getRandomName(), businessImg);

        final Seller newSeller = Seller.of(
                memberCode,
                applyCreateRequest.getStoreName(),
                applyCreateRequest.getStoreRepresentativeName(),
                applyCreateRequest.getBusinessNumber(),
                applyCreateRequest.getMosNumber(),
                applyCreateRequest.getStoreType(),
                applyCreateRequest.getApplyContent(),
                IMAGE_URL + replaceFileName
        );

        final Seller seller = applyRepository.save(newSeller);

        return seller.getSellerCode();
    }

    @Transactional
    public void modify(Long sellerCode, ApplyUpdateRequest applyRequest, MultipartFile businessImg, Long memberCode) {

        Seller seller = applyRepository.findBySellerCodeAndApplyStatusNot(sellerCode, CANCEL)
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_CHECKING_STATUS));

        // 사용자가 신청한 것인지 확인
        if (!seller.getMemberCode().equals(memberCode)) {
            throw new NotFoundException(ExceptionCode.ACCESS_DENIED);
        }

        if(businessImg != null && !businessImg.isEmpty()) {
            String replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, getRandomName(), businessImg);
            FileUploadUtils.deleteFile(IMAGE_DIR, seller.getBusinessImg().replace(IMAGE_URL, ""));
            seller.modifyBusinessImg(IMAGE_URL + replaceFileName);
        }

        seller.modify(
                sellerCode,
                applyRequest.getStoreName(),
                applyRequest.getStoreRepresentativeName(),
                applyRequest.getBusinessNumber(),
                applyRequest.getMosNumber(),
                applyRequest.getStoreType(),
                applyRequest.getApplyContent()
        );
    }

    @Transactional
    public void cancel(Long sellerCode, ApplyUpdateRequest applyRequest, Long memberCode) {

        log.info("Processing cancel for sellerCode: {}, with applyRequest: {}", sellerCode, applyRequest);

        Seller seller = applyRepository.findBySellerCodeAndApplyStatus(sellerCode, ApplyStatus.CHECKING)
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_APPLY_CODE));

        // 사용자가 신청한 것인지 확인
        if (!seller.getMemberCode().equals(memberCode)) {
            throw new NotFoundException(ExceptionCode.ACCESS_DENIED);
        }
            seller.cancel(applyRequest.getApplyStatus());
    }


    // admin

    @Transactional(readOnly = true)
    public Page<AdminApplyResponse> getAdminApplies(Integer page) {

        Page<AdminApplyResponse> applies = applyRepository.getAdminApplies(getPageable(page), ApplyStatus.CHECKING);

        return applies;
    }

    @Transactional(readOnly = true)
    public AdminApplyResponse getAdminApplyDetail(Long sellerCode) {

        AdminApplyResponse apply = applyRepository.getApplyDetail(sellerCode, ApplyStatus.CHECKING)
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_SELLER_CODE));

        return apply;
    }
}
