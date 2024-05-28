package yep.greenFire.greenfirebackend.challenge.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import yep.greenFire.greenfirebackend.challenge.domain.entity.CsContent;

import java.util.Date;

@Getter
@RequiredArgsConstructor

public class CsResponse {

    private int csCode;
    private int memberCode;
    private String csStatus;
    private Date csWriteDate;
    private String csReply;
    private String csReplyStatus;

    public CsResponse(int csCode, int memberCode, String csStatus, Date csWriteDate, String csReply, String csReplyStatus) {
        this.csCode = csCode;
        this.memberCode = memberCode;
        this.csStatus = csStatus;
        this.csWriteDate = csWriteDate;
        this.csReply = csReply;
        this.csReplyStatus = csReplyStatus;
    }

    public static CsResponse from(CsContent csContent) {
        return new CsResponse(
                csContent.getCsCode(),
                csContent.getMemberCode(),
                csContent.getCsStatus(),
                csContent.getCsWriteDate(),
                csContent.getCsReply(),
                csContent.getCsReplyStatus()

        );

    }
}
