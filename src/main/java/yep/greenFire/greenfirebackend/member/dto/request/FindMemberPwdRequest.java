package yep.greenFire.greenfirebackend.member.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FindMemberPwdRequest {

    @NotBlank
    private String memberId;

    @NotBlank
    @Email
    private String memberPassword;
}
