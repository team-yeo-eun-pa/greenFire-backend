package yep.greenFire.greenfirebackend.admin.notice.dto.response;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import yep.greenFire.greenfirebackend.admin.notice.domain.entity.Notice;

import java.util.Date;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberNoticesResponse {

    private final int noticeCode;
    private final String noticeTitle;
    private final String noticeFixable;
    private final Date noticeDate;
    private final Integer noticeWriter;


    public static MemberNoticesResponse from(final Notice notice){
        return new MemberNoticesResponse(
                notice.getNoticeCode(),
                notice.getNoticeTitle(),
                notice.getNoticeFixable(),
                notice.getNoticeDate(),
                notice.getNoticeWriter()

        );
    }
}
