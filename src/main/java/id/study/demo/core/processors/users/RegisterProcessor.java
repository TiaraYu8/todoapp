package id.study.demo.core.processors.users;

import id.study.demo.common.model.dto.users.UserRequestDTO;
import id.study.demo.common.model.dto.users.UserResponseDTO;
import id.study.demo.common.utils.ParamChecker;
import id.study.demo.services.UserService;
import org.springframework.stereotype.Component;

@Component
public class RegisterProcessor {
    private UserService userService;

    public UserResponseDTO process(UserRequestDTO requestDTO){

        ParamChecker.notEmpty(requestDTO.getEmail(),"email");
        ParamChecker.notEmpty(requestDTO.getUsername(),"username");
        ParamChecker.notEmpty(requestDTO.getPassword(), "password");

        return userService.registerUser(requestDTO);
    }
}
