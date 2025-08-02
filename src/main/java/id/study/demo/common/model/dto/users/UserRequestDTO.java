package id.study.demo.common.model.dto.users;


import id.study.demo.common.model.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRequestDTO extends BaseDTO {
    private String username;
    private String email;
    private String password;
}
