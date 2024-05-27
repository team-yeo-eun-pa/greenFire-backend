package yep.greenFire.greenfirebackend.challenge.dto.response;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import yep.greenFire.greenfirebackend.challenge.domain.entity.CsList;

import java.util.Date;

@Getter
@RequiredArgsConstructor
@Repository
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

    public static CsResponse from(CsList csList) {
        return new CsResponse(
                csList.getCsCode(),
                csList.getMemberCode(),
                csList.getCsStatus(),
                csList.getCsWriteDate(),
                csList.getCsReply(),
                csList.getCsReplyStatus()

        );

    }
}
