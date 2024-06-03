package yep.greenFire.greenfirebackend.inquiry.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "tbl_inquiryCate")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class InquiryCateContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer inquiryCateCode;
    private String inquiryCateName;

    public InquiryCateContent(Integer inquiryCateCode, String inquiryCateName) {
        this.inquiryCateCode = inquiryCateCode;
        this.inquiryCateName = inquiryCateName;
    }

}
