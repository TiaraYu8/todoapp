package id.study.demo.common.model.dto.users;

import id.study.demo.common.model.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class UserResponseDTO extends BaseDTO {
    private String id;
    private String username;
    private String email;
    private String password;
    private LocalDateTime createdAt;
}
