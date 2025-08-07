package id.study.demo.services;

import id.study.demo.common.model.dto.session.SessionRequestDTO;
import id.study.demo.common.model.dto.session.SessionResponseDTO;

import java.util.Optional;

public interface SessionService {
    Optional<SessionResponseDTO> findActiveSession( String sessionId);

    SessionResponseDTO createSession(String userId);

    void prolongSession(String sessionId);

    void invalidateSession(String sessionId);

    Optional<SessionResponseDTO> findSession(String sessionId);
}
