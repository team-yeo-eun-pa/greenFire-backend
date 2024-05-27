package yep.greenFire.greenfirebackend.challenge.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Getter
public class CsContent {

    @Id
    private Long csCode;
    private String csContent;

}
