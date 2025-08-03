package id.study.demo.controllers;

import id.study.demo.common.mapper.UserMapper;
import id.study.demo.common.model.dto.users.UserRequestDTO;
import id.study.demo.common.model.dto.users.UserResponseDTO;
import id.study.demo.common.model.vo.users.UserView;
import id.study.demo.core.processors.users.RegisterProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final RegisterProcessor registerProcessor;
    private final UserMapper userMapper;

    @PostMapping("/register")
    public ResponseEntity<UserView> register(@RequestBody UserRequestDTO requestDTO){
        UserResponseDTO responseDTO = registerProcessor.process(requestDTO);
        UserView userView = userMapper.toVO(responseDTO);

        return ResponseEntity.ok(userView);
    }
}
