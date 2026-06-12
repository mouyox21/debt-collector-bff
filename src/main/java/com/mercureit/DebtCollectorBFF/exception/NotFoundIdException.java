package com.mercureit.DebtCollectorBFF.exception;

public class NotFoundIdException extends APIErrorException {


    public NotFoundIdException(ErrorCode code) {
        super(code);
    }


}
