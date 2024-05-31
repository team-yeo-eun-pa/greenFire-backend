package yep.greenFire.greenfirebackend.admin.notice.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import yep.greenFire.greenfirebackend.admin.notice.domain.entity.Notice;

import java.util.Date;

@Getter
@Setter
@ToString
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class AdminNoticesResponse {

    private final Long noticeCode;
    private final String noticeTitle;
    private final String noticeFixable;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private final Date noticeDate;
    private final Long noticeWriter;
    private final String memberName;

//    public static AdminNoticesResponse from(final Notice notice){
//        return new AdminNoticesResponse(
//                notice.getNoticeCode(),
//                notice.getNoticeTitle(),
//                notice.getNoticeFixable(),
//                notice.getNoticeDate(),
//                notice.getNoticeWriter()
//
//        );
//    }

}
