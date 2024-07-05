package yep.greenFire.greenfirebackend.member.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ResetPasswordRequest {

    @NotNull
    private Long memberCode;

    @NotNull
    private String verificationCode;

    @NotBlank
    private String newPassword;

}
