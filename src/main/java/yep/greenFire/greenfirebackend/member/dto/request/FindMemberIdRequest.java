package yep.greenFire.greenfirebackend.member.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FindMemberIdRequest {

    @NotBlank
    @Email
    private String memberEmail;

}
