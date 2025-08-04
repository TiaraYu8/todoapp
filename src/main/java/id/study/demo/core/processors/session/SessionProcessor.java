package id.study.demo.core.processors.session;

import id.study.demo.common.mapper.SessionMapper;
import id.study.demo.common.model.dto.session.SessionRequestDTO;
import id.study.demo.common.model.dto.users.UserResponseDTO;
import id.study.demo.common.model.vo.session.SessionView;
import id.study.demo.common.utils.AssertUtil;
import id.study.demo.common.utils.ParamChecker;
import id.study.demo.services.SessionService;
import id.study.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SessionProcessor {
    private final SessionService sessionService;
    private final SessionMapper sessionMapper;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public SessionView processor(SessionRequestDTO requestDTO){
        ParamChecker.notEmpty(requestDTO.getEmail(), "email");
        ParamChecker.isEmail(requestDTO.getEmail(), "email");
        ParamChecker.notEmpty(requestDTO.getEmail(), "password");

        Optional<UserResponseDTO> optionalUserResponseDTO = userService.findUserByEmail(requestDTO.getEmail());
        AssertUtil.notNull(optionalUserResponseDTO.orElse(null), "User Not Found");

        UserResponseDTO user = optionalUserResponseDTO.get();

        AssertUtil.isTrue(passwordEncoder.matches(requestDTO.getPassword(), use));
    }
}
