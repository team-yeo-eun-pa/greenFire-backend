package yep.greenFire.greenfirebackend.admin.member.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import yep.greenFire.greenfirebackend.admin.member.domain.type.AdminMemberStatus;
import yep.greenFire.greenfirebackend.user.member.domain.type.MemberStatus;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "tbl_member")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class AdminMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberCode;

    private String memberId;
    private String memberName;
    private String memberNickname;
    private String memberEmail;
    private String memberPhone;

    @Enumerated(EnumType.STRING)
    private AdminMemberStatus memberStatus;

    @Temporal(TemporalType.TIMESTAMP)
    private Date registAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date quitAt;

    private Long reportCount;

    public void increaseReportCount(Long reportCount) {
        this.reportCount= reportCount;
    }

    private LocalDateTime suspendedEndDate;

}
