package id.study.demo.services.impl;

import id.study.demo.common.exception.NotFoundException;
import id.study.demo.common.mapper.SessionMapper;
import id.study.demo.common.model.dao.SessionModel;
import id.study.demo.common.model.dto.session.SessionResponseDTO;
import id.study.demo.repository.SessionRepository;
import id.study.demo.services.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {
    private final SessionRepository sessionRepository;
    private final SessionMapper sessionMapper;

    @Override
    public SessionResponseDTO createSession(String userId){

        SessionModel sessionModel = new SessionModel();

        sessionModel.setId(UUID.randomUUID().toString());
        sessionModel.setUserId(userId);
        sessionModel.setExpired(false);
        sessionModel.setCreatedAt(LocalDateTime.now());
        sessionModel.setUpdatedAt(LocalDateTime.now());
        System.out.println("CreatedAt to be saved: " + LocalDateTime.now());


        LocalDateTime expired = LocalDateTime.now().plusDays(1);
        sessionModel.setExpiredDate(expired);
        System.out.println("Expired to be saved: " + expired);



        SessionModel savedSession = sessionRepository.save(sessionModel);

        return sessionMapper.toResponseDTO(savedSession);
    }

    @Override
    public Optional<SessionResponseDTO> findActiveSession(String sessionId){
        return sessionRepository.findActiveSession(sessionId)
                .map(sessionMapper::toResponseDTO);
    }

    @Override
    public Optional<SessionResponseDTO> findSession(String sessionId){
        return sessionRepository.findById(sessionId)
                .map(sessionMapper::toResponseDTO);
    }

    @Override
    public void prolongSession(String sessionId){
        Optional<SessionModel> userActive = sessionRepository.findActiveSession(sessionId);

        if(userActive.isEmpty()) return;

        SessionModel session = userActive.get();
        session.setExpired(false);
        session.setExpiredDate(LocalDateTime.now().plusDays(1));
        session.setUpdatedAt(LocalDateTime.now());

        sessionRepository.save(session);
    }

    @Transactional
    public void invalidateSession(String sessionId) {
        Optional<SessionModel>sessionModel = sessionRepository.findByIdAndExpiredFalse(sessionId);

        if(sessionModel.isPresent()) sessionRepository.invalidateSession(sessionId);
        else throw new NotFoundException("Session already invalidate");
    }
}
