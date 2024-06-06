package yep.greenFire.greenfirebackend.member.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProfileUpdateRequest {

    // 프로필 사진
    private final String memberNickname;
    @Email
    @NotBlank
    private final String memberEmail;
    @NotBlank
    private final String memberPhone;

}
