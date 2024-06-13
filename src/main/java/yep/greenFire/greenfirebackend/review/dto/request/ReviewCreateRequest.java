package yep.greenFire.greenfirebackend.review.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import yep.greenFire.greenfirebackend.order.domain.type.OrderStatus;


@Getter
public class ReviewCreateRequest {

    private String reviewTitle;
    private String reviewContent;
    private Long orderDetailCode;


}
