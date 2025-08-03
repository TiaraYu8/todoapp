package id.study.demo.core.processors.users;

import id.study.demo.common.model.dto.users.UserRequestDTO;
import id.study.demo.common.model.dto.users.UserResponseDTO;
import id.study.demo.common.utils.ParamChecker;
import id.study.demo.core.context.user.RegisterContext;
import id.study.demo.services.UserService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RegsiterProcessor {
    private final UserService userService;

    public void process(RegisterContext context){
        UserRequestDTO requestDTO = context.getRequest();

        ParamChecker.notEmpty(requestDTO.getEmail(),"email");
        ParamChecker.notEmpty(requestDTO.getUsername(),"username");
        ParamChecker.notEmpty(requestDTO.getPassword(), "password");

        UserResponseDTO responseDTO = userService.registerUser(requestDTO);

        context.setResponse(responseDTO);
    }
}
