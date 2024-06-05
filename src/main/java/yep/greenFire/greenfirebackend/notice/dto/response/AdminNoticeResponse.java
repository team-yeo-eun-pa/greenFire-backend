package yep.greenFire.greenfirebackend.notice.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Getter
@ToString
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class AdminNoticeResponse {

    private final Long noticeCode;
    private final String noticeTitle;
    private final String noticeContent;
    private final Date noticeDate;
    private final Long noticeWriter;
    private final String memberName;

}
