package id.study.demo.core.processors.users;

import id.study.demo.common.mapper.UserMapper;
import id.study.demo.common.model.dto.users.UserRequestDTO;
import id.study.demo.common.model.dto.users.UserResponseDTO;
import id.study.demo.common.utils.AssertUtil;
import id.study.demo.common.utils.ParamChecker;
import id.study.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRegisterProcessor {
    private final UserService userService;

    public void process(UserRequestDTO requestDTO){

        ParamChecker.notEmpty(requestDTO.getEmail(),"email");
        ParamChecker.isEmail(requestDTO.getEmail(),"email");
        ParamChecker.notEmpty(requestDTO.getUsername(),"username");
        ParamChecker.minLength(requestDTO.getUsername(),5, "username");
        ParamChecker.maxLength(requestDTO.getUsername(),25, "username");
        ParamChecker.notEmpty(requestDTO.getPassword(), "password");
        ParamChecker.minLength(requestDTO.getPassword(),8, "password");

        AssertUtil.isTrue(
                userService.findUserByEmail(requestDTO.getEmail()).isEmpty(),
                "Email already in use"
        );
        AssertUtil.isTrue(
                userService.findUserByUsername(requestDTO.getUsername()).isEmpty(),
                "Email already in use"
        );

        UserResponseDTO responseDTO = userService.registerUser(requestDTO);

//        return userMapper.toVO(responseDTO);
    }
}
