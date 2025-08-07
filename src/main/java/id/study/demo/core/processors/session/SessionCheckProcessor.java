package id.study.demo.core.processors.session;

import id.study.demo.common.model.dto.session.SessionCheckRequestDTO;
import id.study.demo.common.model.dto.session.SessionResponseDTO;
import id.study.demo.common.utils.AssertUtil;
import id.study.demo.common.utils.ParamChecker;
import id.study.demo.services.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SessionCheckProcessor {
    private final SessionService sessionService;

    public void process(SessionCheckRequestDTO requestDTO){
        ParamChecker.notEmpty(requestDTO.getSessionId(), "sessionId");

        Optional<SessionResponseDTO> session = sessionService.findActiveSession(requestDTO.getSessionId());
        AssertUtil.notNull(session, "Your session is expired" );

        boolean sessionActive = session.isPresent() && (!session.get().isExpired() && LocalDateTime.now().isBefore(session.get().getExpiredDate()));
        AssertUtil.isTrue(sessionActive, "Your session is Expired");

        sessionService.prolongSession(requestDTO.getSessionId());
    }
}
