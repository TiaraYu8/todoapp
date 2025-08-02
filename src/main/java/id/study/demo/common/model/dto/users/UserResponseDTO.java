package id.study.demo.common.model.dto.users;

import id.study.demo.common.model.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
public class UserResponseDTO extends BaseDTO {
    private UUID id;
    private String username;
    private String email;
    private LocalDateTime createdAt;
}
