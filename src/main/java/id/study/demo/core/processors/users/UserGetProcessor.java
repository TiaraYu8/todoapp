package id.study.demo.core.processors.users;

import java.util.Optional;

import org.springframework.stereotype.Component;

import id.study.demo.common.exception.BusinessException;
import id.study.demo.common.mapper.UserMapper;
import id.study.demo.common.model.dto.session.SessionResponseDTO;
import id.study.demo.common.model.dto.users.UserFindRequest;
import id.study.demo.common.model.dto.users.UserResponseDTO;
import id.study.demo.common.model.vo.users.UserView;
import id.study.demo.common.utils.AssertUtil;
import id.study.demo.common.utils.ParamChecker;
import id.study.demo.services.SessionService;
import id.study.demo.services.UserService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserGetProcessor {
    private final UserService    userService;
    private final SessionService sessionService;
    private final UserMapper     userMapper;

    public UserView process(UserFindRequest request) {
        ParamChecker.notEmpty(request.getSessionId(), "sessionId");

        Optional<SessionResponseDTO> sessionResponseDTO = sessionService
            .findSession(request.getSessionId());
        AssertUtil.isTrue(sessionResponseDTO.isPresent(), "Session not found");

        UserResponseDTO userResponseDTO = userService
            .findUserById(sessionResponseDTO.get().getUserId())
            .orElseThrow(() -> new BusinessException("NOT_FOUND",
                String.format("User not found for session: %s", request.getSessionId())));
        return userMapper.toVO(userResponseDTO);
    }
}
