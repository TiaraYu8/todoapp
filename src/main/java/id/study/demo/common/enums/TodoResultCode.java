package id.study.demo.common.enums;

import id.study.demo.common.enums.intf.ResultCode;
import org.springframework.http.HttpStatus;

public enum TodoResultCode implements ResultCode {
    SUCCESS("SUCCESS", HttpStatus.OK, "Operation successful"),
    USERNAME_EXISTS("USERNAME_EXISTS", HttpStatus.BAD_REQUEST, "Username already exists"),
    EMAIL_EXISTS("EMAIL_EXISTS", HttpStatus.BAD_REQUEST, "Email already exists"),
    VALIDATION_ERROR("VALIDATION_ERROR", HttpStatus.BAD_REQUEST, "Validation error"),
    SERVER_ERROR("SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");

    private final String code;
    private final HttpStatus status;
    private final String description;

    TodoResultCode(String code, HttpStatus status, String description){
        this.code = code;
        this.status = status;
        this.description = description;
    }

    @Override
    public String getCode(){return code;}

    @Override
    public String getDescription(){ return description;}

    @Override
    public int getStatus(){return status.value();}

}
