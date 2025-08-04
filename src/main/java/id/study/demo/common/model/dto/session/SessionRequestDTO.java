package id.study.demo.common.model.dto.session;


import id.study.demo.common.model.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SessionRequestDTO extends BaseDTO {
    private String email;
    private String password;
}
