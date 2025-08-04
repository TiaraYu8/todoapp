package id.study.demo.core.processors.users;

import id.study.demo.common.mapper.UserMapper;
import id.study.demo.common.model.dto.users.UserRequestDTO;
import id.study.demo.common.model.dto.users.UserResponseDTO;
import id.study.demo.common.model.vo.users.UserView;
import id.study.demo.common.utils.ParamChecker;
import id.study.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegisterProcessor {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserView process(UserRequestDTO requestDTO){

        ParamChecker.notEmpty(requestDTO.getEmail(),"email");
        ParamChecker.notEmpty(requestDTO.getUsername(),"username");
        ParamChecker.notEmpty(requestDTO.getPassword(), "password");

        UserResponseDTO responseDTO = userService.registerUser(requestDTO);

        return userMapper.toVO(responseDTO);
    }
}
