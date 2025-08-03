package id.study.demo.core.context.user;

import id.study.demo.common.model.dto.users.UserRequestDTO;
import id.study.demo.common.model.dto.users.UserResponseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterContext {
    private final UserRequestDTO request;
    private UserResponseDTO response;
    private String decodedPassword;

    public RegisterContext(UserRequestDTO request) {
        this.request = request;
    }
}