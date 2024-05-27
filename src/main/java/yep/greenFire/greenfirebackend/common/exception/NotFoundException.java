package yep.greenFire.greenfirebackend.common.exception;

import lombok.Getter;
import yep.greenFire.greenfirebackend.common.exception.type.ExceptionCode;

@Getter
public class NotFoundException extends CustomException {
    public NotFoundException(final ExceptionCode exceptionCode) {
        super(exceptionCode);
    }

}
