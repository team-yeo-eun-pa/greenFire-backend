package yep.greenFire.greenfirebackend.challenge.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import yep.greenFire.greenfirebackend.challenge.domain.entity.CsContent;

import java.util.Date;

@Getter
@RequiredArgsConstructor

public class CsResponse {

    //전체 조회시 보여줘야 할 값이 뭐가 있을까.

    private int csCode;
    private String csStatus;
    private String csReply;
    private String csReplyStatus;
    private int memberCode;

    public CsResponse(int csCode, String csStatus, String csReply, String csReplyStatus, int memberCode) {
        this.csCode = csCode;
        this.csStatus = csStatus;
        this.csReply = csReply;
        this.csReplyStatus = csReplyStatus;
        this.memberCode = memberCode;
    }

    public static CsResponse from(CsContent csContent) {
        return new CsResponse(
                csContent.getCsCode(),
                csContent.getCsStatus(),
                csContent.getCsReply(),
                csContent.getCsReplyStatus(),
                csContent.getMemberCode()

        );

    }
}
