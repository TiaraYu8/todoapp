package id.study.demo.repository;

import id.study.demo.common.model.dao.SessionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SessionRepository extends JpaRepository<SessionModel, String> {
    @Query("SELECT s FROM SessionModel s WHERE s.id = :sessionId AND s.expired = false AND s.expiredDate > CURRENT_TIMESTAMP")
    Optional<SessionModel> findActiveSession(@Param("sessionId") String sessionId);

    @Modifying
    @Query("UPDATE SessionModel s SET s.expired = true WHERE s.id = :sessionId")
    void invalidateSession(@Param("sessionId") String sessionId);

    Optional<SessionModel> findByIdAndExpiredFalse(String sessionId);

    Optional<SessionModel> findById(String id);

    Optional<SessionModel> findUserIdById(String id);
}
