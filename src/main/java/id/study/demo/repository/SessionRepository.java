package id.study.demo.repository;

import id.study.demo.common.model.dao.SessionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SessionRepository extends JpaRepository<SessionModel, String> {
    @Query("SELECT s FROM SessionModel s WHERE s.sessionId = :sessionId AND s.expired = false AND s.expiredDate > CURRENT_TIMESTAMP")
    Optional<SessionModel> findActiveSession(@Param("sessionId") String sessionId);

}
