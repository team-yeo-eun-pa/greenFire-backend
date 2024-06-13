package yep.greenFire.greenfirebackend.review.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
public class ReviewResponse {

    private final Long productCode;
    private final String productName;
    private final String reviewTitle;
    private final String reviewContent;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private final LocalDateTime reviewDate;
    private final LocalDateTime modifyDate;
    private final Long memberCode;
    private final String memberNickname;

    public ReviewResponse(Long productCode, String productName, String reviewTitle, String reviewContent, LocalDateTime reviewDate, LocalDateTime modifyDate, Long memberCode, String memberNickname) {
        this.productCode = productCode;
        this.productName = productName;
        this.reviewTitle = reviewTitle;
        this.reviewContent = reviewContent;
        this.reviewDate = reviewDate;
        this.modifyDate = modifyDate;
        this.memberCode = memberCode;
        this.memberNickname = memberNickname;
    }
}
