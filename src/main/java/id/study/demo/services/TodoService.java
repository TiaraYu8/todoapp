package id.study.demo.services;

import id.study.demo.common.model.dao.TodoModel;
import id.study.demo.common.model.dto.session.SessionResponseDTO;
import id.study.demo.common.model.dto.todos.TodoCreateRequestDTO;
import id.study.demo.common.model.dto.todos.TodoResponseDTO;
import id.study.demo.common.model.dto.todos.TodoUpdateRequestDTO;

import java.util.Optional;

public interface TodoService {
    TodoResponseDTO createTodo(TodoCreateRequestDTO todoCreateRequestDTO, String userId);

    Optional<TodoResponseDTO> findById(String id);

    Optional<TodoResponseDTO> findByUserId(String userId);

    Optional<TodoResponseDTO> findIncomplete(String todoId);

    Optional<TodoResponseDTO> findCompleted(String todoId);

    Optional<TodoResponseDTO> findTodo(String todoId);

    void deleteTodo(String todoId);

    TodoResponseDTO updateTodo(TodoUpdateRequestDTO todoUpdateRequestDTO);

    void completeTodo(String todoId);
}
