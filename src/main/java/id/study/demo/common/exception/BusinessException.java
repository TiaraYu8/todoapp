package id.study.demo.common.exception;

import id.study.demo.common.enums.intf.ResultCode;

public class BusinessException extends RuntimeException{
    private final ResultCode resultCode;

    public BusinessException(ResultCode resultCode, String message){
        super(message);
        this.resultCode = resultCode;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }
}
