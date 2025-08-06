package id.study.demo.core.configuration.security;

import id.study.demo.common.model.dto.session.SessionCheckRequestDTO;
import id.study.demo.common.model.dto.session.SessionResponseDTO;
import id.study.demo.services.SessionService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SessionAuthFilter extends OncePerRequestFilter {
    private final SessionService sessionService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authSessionToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.isNotEmpty(authSessionToken)) {
            Optional<SessionResponseDTO> session = sessionService.findActiveSession(authSessionToken);
            if (session.isPresent()
                    && (!session.get().isExpired() && LocalDateTime.now().isBefore(session.get().getExpiredDate()))) {
                sessionService.prolongSession(session.get().getId());
                Authentication authentication = new SessionAuthToken(null,
                        authSessionToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}
