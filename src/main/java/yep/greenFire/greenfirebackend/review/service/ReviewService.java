package yep.greenFire.greenfirebackend.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yep.greenFire.greenfirebackend.common.exception.ConflictException;
import yep.greenFire.greenfirebackend.common.exception.NotFoundException;
import yep.greenFire.greenfirebackend.common.exception.type.ExceptionCode;
import yep.greenFire.greenfirebackend.order.domain.type.OrderStatus;
import yep.greenFire.greenfirebackend.order.service.OrderService;
import yep.greenFire.greenfirebackend.review.domain.entity.Review;
import yep.greenFire.greenfirebackend.review.domain.repository.ReviewRepository;
import yep.greenFire.greenfirebackend.review.domain.type.ReviewStatus;
import yep.greenFire.greenfirebackend.review.dto.request.ReviewCreateRequest;
import yep.greenFire.greenfirebackend.review.dto.response.ReviewResponse;

import static yep.greenFire.greenfirebackend.order.domain.type.OrderStatus.COMPLETED;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final OrderService orderService;
    private Pageable getPageable(final Integer page) {
        return PageRequest.of(page - 1, 10, Sort.by("reviewCode").descending());
    }

    @Transactional(readOnly = true)
    public Page<ReviewResponse> reviews(Integer page, Long productCode) {

        return reviewRepository.findByProductCode(getPageable(page), productCode, ReviewStatus.ACTIVE);
    }


    public void save(ReviewCreateRequest reviewCreateRequest, Long memberCode) {

        verifyOrdered(reviewCreateRequest.getOrderDetailCode(), memberCode);
        verifyReviewCreated(reviewCreateRequest.getOrderDetailCode());

        Review newReview = Review.of(
                reviewCreateRequest.getOrderDetailCode(),
                reviewCreateRequest.getReviewTitle(),
                reviewCreateRequest.getReviewContent()
        );

        reviewRepository.save(newReview);

    }

    private void verifyReviewCreated(Long getOrderDetailCode) {
        if(reviewRepository.existsByOrderDetailCode(getOrderDetailCode));
    }

    private void verifyOrdered(Long getOrderDetailCode, Long memberCode) {
        orderService.verifyOrdered(getOrderDetailCode, memberCode);
    }
}
