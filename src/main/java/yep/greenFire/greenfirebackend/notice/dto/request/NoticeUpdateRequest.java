package yep.greenFire.greenfirebackend.notice.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class NoticeUpdateRequest {

    @NotBlank
    private final String noticeTitle;
    @NotBlank
    private final String noticeContent;
}
