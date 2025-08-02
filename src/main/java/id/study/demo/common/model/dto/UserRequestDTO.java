package id.study.demo.common.model.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRequestDTO {
    private String username;
    private String email;
    private String password;
}
