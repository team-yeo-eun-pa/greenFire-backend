package yep.greenFire.greenfirebackend.member.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CheckMemberIdRequest {

    @NotBlank
    private String memberId;

}
