package id.study.demo.repository;

import id.study.demo.common.model.dao.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, UUID> {
    Optional<UserModel> findByEmail(String email);

    Optional<UserModel> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
