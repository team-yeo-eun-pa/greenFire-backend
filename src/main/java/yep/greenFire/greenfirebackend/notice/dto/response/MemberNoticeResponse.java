package yep.greenFire.greenfirebackend.notice.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import yep.greenFire.greenfirebackend.notice.domain.entity.Notice;

import java.util.Date;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberNoticeResponse {

    private final String noticeContent;
    private final String noticeTitle;
    private final String noticeFixable;
    private final Date noticeDate;
    private final Long noticeWriter;

    public static MemberNoticeResponse from(final Notice notice) {
        return new MemberNoticeResponse(
                notice.getNoticeContent(),
                notice.getNoticeTitle(),
                notice.getNoticeFixable(),
                notice.getNoticeDate(),
                notice.getNoticeWriter()
        );
    }
}



