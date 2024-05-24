package yep.greenFire.greenfirebackend.common.exception;

import lombok.Getter;
import yep.greenFire.greenfirebackend.common.exception.type.ExceptionCode;

@Getter
public class ConflictException extends CustomException {
    public ConflictException(final ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}
