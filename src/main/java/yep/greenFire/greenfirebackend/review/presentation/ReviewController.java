package yep.greenFire.greenfirebackend.review.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yep.greenFire.greenfirebackend.common.paging.Pagination;
import yep.greenFire.greenfirebackend.common.paging.PagingButtonInfo;
import yep.greenFire.greenfirebackend.common.paging.PagingResponse;
import yep.greenFire.greenfirebackend.review.dto.response.ReviewResponse;
import yep.greenFire.greenfirebackend.review.service.ReviewService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class ReviewController {

    private final ReviewService reviewService;

    /* 상품별 리뷰 목록 조회 */
    @GetMapping("/products/{productCode}/reviews")
    public ResponseEntity<PagingResponse> getReviews(
            @PathVariable final Long productCode,
            @RequestParam(defaultValue = "1") final Integer page
    ) {

        final Page<ReviewResponse> reviews = reviewService.reviews(page, productCode);
        final PagingButtonInfo pagingButtonInfo = Pagination.getPagingButtonInfo(reviews);
        final PagingResponse pagingResponse = PagingResponse.of(reviews.getContent(), pagingButtonInfo);

        return ResponseEntity.ok(pagingResponse);
    }
}
