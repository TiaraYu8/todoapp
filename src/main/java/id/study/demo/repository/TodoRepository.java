package id.study.demo.repository;

import id.study.demo.common.model.dao.TodoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<TodoModel, String> {
    Optional<TodoModel> findById(String todoId);
    Optional<TodoModel> findByUserId(String userId);

    Optional<TodoModel> findByIdAndCompletedTrue(String todoId);

    Optional<TodoModel> findByIdAndCompletedFalse(String todoId);

    Optional<TodoModel>findByIdAndDeletedFalse(String todoId);

    @Modifying
    @Query("UPDATE TodoModel s SET s.deleted = true WHERE s.id = :todoId")
    void softDeleteById(@Param("todoId") String todoId);

    @Modifying
    @Query("UPDATE TodoModel s SET s.completed = true WHERE s.id = :todoId")
    void completedById(@Param("todoId") String todoId);
}
