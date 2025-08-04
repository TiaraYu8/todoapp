package id.study.demo.services.impl;

import id.study.demo.common.exception.NotFoundException;
import id.study.demo.common.mapper.SessionMapper;
import id.study.demo.common.mapper.UserMapper;
import id.study.demo.common.model.dao.SessionModel;
import id.study.demo.common.model.dao.UserModel;
import id.study.demo.common.model.dto.session.SessionRequestDTO;
import id.study.demo.common.model.dto.session.SessionResponseDTO;
import id.study.demo.common.utils.AssertUtil;
import id.study.demo.repository.SessionRepository;
import id.study.demo.repository.UserRepository;
import id.study.demo.services.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {
    private final SessionRepository sessionRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final SessionMapper sessionMapper;

    @Override
    public SessionResponseDTO createSession(SessionRequestDTO requestDTO, String userId){

        Optional<UserModel> user = userRepository.findByEmail(requestDTO.getEmail());
//        AssertUtil.isTrue(passwordEncoder.matches(requestDTO.getPassword(), user.));

        SessionModel sessionModel = new SessionModel();

        sessionModel.setId(UUID.randomUUID().toString());
        sessionModel.setUserId(userId);
        sessionModel.setExpired(false);
        sessionModel.setExpiredDate(LocalDateTime.now().plusDays(1));
        sessionModel.setCreatedAt(LocalDateTime.now());
        sessionModel.setUpdatedAt(LocalDateTime.now());

        SessionModel savedSession = sessionRepository.save(sessionModel);

        return sessionMapper.toResponseDTO(savedSession);
    }

    @Override
    public Optional<SessionResponseDTO> findActiveSession(String sessionId){
        return sessionRepository.findActiveSession(sessionId)
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
    }
}
