package yep.greenFire.greenfirebackend.auth.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class TokenDTO {

    private final String accessToken;
    private final String refreshToken;

    public static TokenDTO of(String accessToken, String refreshToken) {
        return new TokenDTO(accessToken,refreshToken);
    }
}
