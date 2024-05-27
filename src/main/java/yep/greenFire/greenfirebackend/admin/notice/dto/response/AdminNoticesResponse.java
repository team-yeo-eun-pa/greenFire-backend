package yep.greenFire.greenfirebackend.admin.notice.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import yep.greenFire.greenfirebackend.admin.notice.domain.entity.Notice;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AdminNoticesResponse {

    private final int noticeCode;
    private final String noticeTitle;
    private final String noticeFixable;
    private final Date noticeDate;
    private final Integer noticeWriter;

    public static AdminNoticesResponse from(final Notice notice){
        return new AdminNoticesResponse(
                notice.getNoticeCode(),
                notice.getNoticeTitle(),
                notice.getNoticeFixable(),
                notice.getNoticeDate(),
                notice.getNoticeWriter()

        );
    }

}
