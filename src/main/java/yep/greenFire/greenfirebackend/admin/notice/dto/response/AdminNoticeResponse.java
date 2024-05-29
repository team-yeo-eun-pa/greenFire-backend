package yep.greenFire.greenfirebackend.admin.notice.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import yep.greenFire.greenfirebackend.admin.notice.domain.entity.Notice;

import java.util.Date;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AdminNoticeResponse {

    private final String noticeContent;
    private final String noticeTitle;
    private final String noticeFixable;
    private final Date noticeDate;
    private final Integer noticeWriter;

    public static AdminNoticeResponse from(final Notice notice) {
        return new AdminNoticeResponse(
                notice.getNoticeContent(),
                notice.getNoticeTitle(),
                notice.getNoticeFixable(),
                notice.getNoticeDate(),
                notice.getNoticeWriter()
        );
    }
}
