package yep.greenFire.greenfirebackend.admin.notice.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import yep.greenFire.greenfirebackend.user.member.domain.type.MemberStatus;

import java.util.Date;

@Entity
@Table(name = "tbl_member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EntityListeners(AuditingEntityListener.class)
public class AdminMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int memberCode;

    private String memberId;
    private String memberName;
    private String memberNickname;
    private String memberEmail;
    private String memberPhone;

    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus;

    @Temporal(TemporalType.TIMESTAMP)
    private Date registAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date quitAt;

}
