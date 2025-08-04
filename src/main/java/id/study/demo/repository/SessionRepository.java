package id.study.demo.repository;

import id.study.demo.common.model.dao.SessionModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<SessionModel, String> {
}
