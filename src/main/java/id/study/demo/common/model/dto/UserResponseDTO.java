package id.study.demo.common.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
public class UserResponseDTO {
    private UUID id;
    private String username;
    private String email;
    private LocalDateTime createdAt;
}
