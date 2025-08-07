package id.study.demo.core.processors.session;

import id.study.demo.common.model.dto.session.SessionLogoutRequestDTO;
import id.study.demo.common.utils.ParamChecker;
import id.study.demo.services.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SessionLogoutProcessor {
    private final SessionService sessionService;

    public void process(SessionLogoutRequestDTO requestDTO){
        ParamChecker.notEmpty(requestDTO.getSessionId(), "sessionId");

        sessionService.invalidateSession(requestDTO.getSessionId());
    }
}
