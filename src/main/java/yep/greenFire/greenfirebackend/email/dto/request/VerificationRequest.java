package yep.greenFire.greenfirebackend.email.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class VerificationRequest {

    private Long memberCode;
    private String verificationCode;

}
