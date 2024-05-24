package yep.greenFire.greenfirebackend.admin.notice.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import yep.greenFire.greenfirebackend.admin.notice.domain.entity.Notice;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AdminNoticesResponse {

    private final int noticeCode;
    private final String noticeTitle;
    private final String noticeFixable;
    private final LocalDate noticeDate;
    private final String noticeWriter;

    public static AdminNoticesResponse from(final Notice notice){
        return new AdminNoticesResponse(
                notice.getNoticeCode(),
                notice.getNoticeTitle(),
                notice.getNoticeFixable(),
                notice.getNoticeDate(),
                notice.getNoticeWriter().getMemberName()

        );
    }

}
