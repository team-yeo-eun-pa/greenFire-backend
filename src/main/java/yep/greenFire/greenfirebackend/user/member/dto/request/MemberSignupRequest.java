package yep.greenFire.greenfirebackend.user.member.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MemberSignupRequest {

    @NotBlank
    private final String memberId;
    @NotBlank
    private final String memberPassword;
    @NotBlank
    private final String memberName;
    private final String memberNickname;
    @NotBlank
    private final String memberEmail;
    @NotBlank
    private final String memberPhone;

    @NotBlank
    private final String addressSido;
    @NotBlank
    private final String addressSigungu;
    @NotBlank
    private final String addressDongeupmyeon;
    @NotBlank
    private final String addressDetail;
    @NotBlank
    private final String addressZipcode;

}
