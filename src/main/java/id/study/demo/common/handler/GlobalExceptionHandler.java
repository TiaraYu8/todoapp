package id.study.demo.common.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import id.study.demo.common.exception.BusinessException;
import id.study.demo.common.wrapper.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<Object>> handleBusinessException(BusinessException be) {
        return ResponseEntity.ok()
            .body(ApiResponse.error(be.getResultCode(), be.getResultMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleGeneric(Exception ex) {
        return ResponseEntity.ok()
            .body(ApiResponse.error("INTERNAL_ERROR", "Something went wrong"));
    }
}
