package yep.greenFire.greenfirebackend.common.exception;

import lombok.Getter;
import yep.greenFire.greenfirebackend.common.exception.type.ExceptionCode;

@Getter
public class ServerInternalException extends CustomException {
    public ServerInternalException(final ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}
