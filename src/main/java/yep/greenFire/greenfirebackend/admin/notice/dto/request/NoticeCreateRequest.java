package yep.greenFire.greenfirebackend.admin.notice.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import yep.greenFire.greenfirebackend.admin.notice.domain.entity.AdminMember;

import java.util.Date;

@Getter
@RequiredArgsConstructor
public class NoticeCreateRequest {

    @NotBlank
    private final String noticeTitle;
    @NotBlank
    private final String noticeContent;
    @NotBlank
    private final String noticeFixable;

    private final Integer noticeWriter;

}
