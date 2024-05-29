package yep.greenFire.greenfirebackend.challenge.dto.response;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yep.greenFire.greenfirebackend.challenge.domain.entity.CsContent;

import java.util.Date;

@Getter
@Service
@RequiredArgsConstructor
public class AdminCsResponse {

    // 로그인한 관리자가 마이페이지에서 등록된 문의를 볼 때 필요한 것들이 뭐가 있을까.
    // 상세 문의 내역 보기 전에 테이블에서 보여줄 것들. . ?
    private int csCode;
    private Date csWriteDate;
    private int memberCode;
    //문의 제목이 여기쯤 들어가야 하지 않을까?
    private String csDetail;
    private String csStatus;
    private String csReplyStatus;

    //문의 상세보기에 필요한 내용들
    //위의 변수에 더해서
    private String csReply;
    private Date csModifyDate;
    private Date csDeleteDate;

    public AdminCsResponse(int csCode, Date csWriteDate, int memberCode, String csDetail, String csStatus, String csReplyStatus) {
        this.csCode = csCode;
        this.csWriteDate = csWriteDate;
        this.memberCode = memberCode;
        this.csDetail = csDetail;
        this.csStatus = csStatus;
        this.csReplyStatus = csReplyStatus;
    }


    public static AdminCsResponse from(CsContent adminCsContent) {
        return new AdminCsResponse (
                adminCsContent.getCsCode(),
                adminCsContent.getCsWriteDate(),
                adminCsContent.getMemberCode(),
                adminCsContent.getCsDetail(),
                adminCsContent.getCsStatus(),
                adminCsContent.getCsReplyStatus()
        );
    }
}
