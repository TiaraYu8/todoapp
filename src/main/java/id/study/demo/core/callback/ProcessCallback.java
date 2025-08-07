package id.study.demo.core.callback;

import id.study.demo.common.wrapper.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.function.Supplier;

public class ProcessCallback {
    public static <T>ResponseEntity<ApiResponse<T>> process (Supplier<T> action){
        T result = action.get();
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    public static ResponseEntity<ApiResponse<Void>> execute (Runnable action){
        action.run();
        return ResponseEntity.ok(ApiResponse.success1());
    }
}
