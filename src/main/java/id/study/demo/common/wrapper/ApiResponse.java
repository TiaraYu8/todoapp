package id.study.demo.common.wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse<T> {
    private String responseCode;
    private String responseMessage;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;
    public static <T> ApiResponse<T> success(T data) {
        return ApiResponse.<T>builder()
                .responseCode("SUCCESS")
                .responseMessage("SUCCESS")
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> error(String code, String message) {
        return ApiResponse.<T>builder()
                .responseCode(code)
                .responseMessage(message)
                .build();
    }

    public static ApiResponse<Void> success() {
        return ApiResponse.<Void>builder()
                .responseCode("SUCCESS")
                .responseMessage("SUCCESS")
                .build();
    }
}
