package id.study.demo.controllers;

import id.study.demo.common.model.dto.session.SessionCheckRequestDTO;
import id.study.demo.common.model.dto.session.SessionLogoutRequestDTO;
import id.study.demo.common.model.dto.session.SessionRequestDTO;
import id.study.demo.common.model.vo.session.SessionView;
import id.study.demo.common.wrapper.ApiResponse;
import id.study.demo.core.callback.ProcessCallback;
import id.study.demo.core.processors.session.SessionCheckProcessor;
import id.study.demo.core.processors.session.SessionLoginProcessor;
import id.study.demo.core.processors.session.SessionLogoutProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/session")
public class SessionController {
    private final SessionLoginProcessor loginProcessor;
    private final SessionLogoutProcessor logoutProcessor;
    private final SessionCheckProcessor checkProcessor;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Map<String, String>>> sessionLogin(@RequestBody SessionRequestDTO requestDTO){
        return ProcessCallback.process(() -> {
            String token = loginProcessor.processor(requestDTO);
            return Map.of("sessionToken",token);
        });
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> sessionLogout(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String sessionToken){
        return ProcessCallback.execute(() -> {
            SessionLogoutRequestDTO requestDTO = new SessionLogoutRequestDTO();
            requestDTO.setSessionId(sessionToken);
            logoutProcessor.process(requestDTO);
        });

    }

    @PostMapping("/check")
    public ResponseEntity<ApiResponse<Void>> sessionCheck(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String authToken){
        return ProcessCallback.execute(() -> {
            SessionCheckRequestDTO requestDTO = new SessionCheckRequestDTO();
            requestDTO.setSessionId(authToken);
            checkProcessor.process(requestDTO);
        });

    }

}
