package yep.greenFire.greenfirebackend.notice.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import yep.greenFire.greenfirebackend.member.domain.entity.Member;

@Getter
@RequiredArgsConstructor
@ToString
public class NoticeCreateRequest {

    @NotBlank
    private final String noticeTitle;
    @NotBlank
    private final String noticeContent;

    private final Member noticeWriter;

}
