package yep.greenFire.greenfirebackend.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yep.greenFire.greenfirebackend.review.domain.repository.ReviewRepository;
import yep.greenFire.greenfirebackend.review.domain.type.ReviewStatus;
import yep.greenFire.greenfirebackend.review.dto.response.ReviewResponse;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;

    private Pageable getPageable(final Integer page) {
        return PageRequest.of(page - 1, 10, Sort.by("reviewCode").descending());
    }

    @Transactional(readOnly = true)
    public Page<ReviewResponse> reviews(Integer page, Long productCode) {

        return reviewRepository.findByProductCode(getPageable(page), productCode, ReviewStatus.ACTIVE);
    }
}
