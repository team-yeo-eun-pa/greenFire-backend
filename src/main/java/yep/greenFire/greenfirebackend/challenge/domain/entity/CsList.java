package yep.greenFire.greenfirebackend.challenge.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@Table(name = "tbl_cs")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)

public class CsList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int csCode;
    private Date csWriteDate;
    private String csStatus;
    private Date csModifyDate;
    private Date csDeleteDate;
    private String csReply;
    private String csReplyStatus;
    private int memberCode;
    private String memberId;
    private String memberName;
    private String memberEmail;

    public CsList(int csCode, Date csWriteDate, String csStatus, Date csModifyDate, Date csDeleteDate, String csReply, String csReplyStatus, int memberCode, String memberId, String memberName, String email) {
        this.csCode = csCode;
        this.csWriteDate = csWriteDate;
        this.csStatus = csStatus;
        this.csModifyDate = csModifyDate;
        this.csDeleteDate = csDeleteDate;
        this.csReply = csReply;
        this.csReplyStatus = csReplyStatus;
        this.memberCode = memberCode;
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberEmail = memberEmail;
    }


    public CsList(int memberCode, String memberId, String memberName, String memberEmail) {
        this.memberCode = memberCode;
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberEmail = memberEmail;
    }

    public static CsList of(final int memberCode, final String memberId, final String memberName, final String memberEmail) {

        return new CsList (
                memberCode,
                memberId,
                memberName,
                memberEmail
        );
    }
}
