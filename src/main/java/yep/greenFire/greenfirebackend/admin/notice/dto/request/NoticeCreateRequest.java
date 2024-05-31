package yep.greenFire.greenfirebackend.admin.notice.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class NoticeCreateRequest {

    @NotBlank
    private final String noticeTitle;
    @NotBlank
    private final String noticeContent;
    @NotBlank
    private final String noticeFixable;

    private final Long noticeWriter;

}
