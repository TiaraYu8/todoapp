package id.study.demo.controllers;

import id.study.demo.common.model.dto.session.SessionLogoutRequestDTO;
import id.study.demo.common.model.dto.users.UserFindRequest;
import id.study.demo.common.model.dto.users.UserRequestDTO;
import id.study.demo.common.model.vo.users.UserView;
import id.study.demo.common.wrapper.ApiResponse;
import id.study.demo.core.callback.ProcessCallback;
import id.study.demo.core.processors.users.UserGetProcessor;
import id.study.demo.core.processors.users.UserRegisterProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserRegisterProcessor userRegisterProcessor;
    private final UserGetProcessor userGetProcessor;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Void>> register(@RequestBody UserRequestDTO requestDTO) {
        return ProcessCallback.execute(() -> userRegisterProcessor.process(requestDTO));
    }

    @PostMapping("/me")
    public ResponseEntity<ApiResponse<UserView>> findUser(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String sessionToken) {
        return ProcessCallback.process(() -> {
            UserFindRequest requestDTO = new UserFindRequest();
            requestDTO.setSessionId(sessionToken);
            return userGetProcessor.process(requestDTO);
        });
    }
}
