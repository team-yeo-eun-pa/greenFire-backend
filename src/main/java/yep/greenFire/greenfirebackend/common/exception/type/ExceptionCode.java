package yep.greenFire.greenfirebackend.common.exception.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ExceptionCode {
    MEMBER_NOT_FOUNd(1000, "해당하는 회원을 찾을 수 없습니다."),

}
