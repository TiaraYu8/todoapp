package id.study.demo.common.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private final String resultCode;
    private final String resultMessage;

    public BusinessException(String resultCode, String resultMessage) {
        super(resultMessage);
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }
}
