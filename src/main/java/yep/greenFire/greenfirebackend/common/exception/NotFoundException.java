package yep.greenFire.greenfirebackend.common.exception;

import lombok.Getter;

@Getter
public class NotFoundException  extends CustomException{

    public NotFoundException(final ExceptionCode exceptionCode){
        super(exceptionCode);
    }
}
