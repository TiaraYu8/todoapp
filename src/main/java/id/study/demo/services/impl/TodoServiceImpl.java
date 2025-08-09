package id.study.demo.services.impl;

import id.study.demo.common.exception.BadRequestException;
import id.study.demo.common.exception.NotFoundException;
import id.study.demo.common.mapper.TodoMapper;
import id.study.demo.common.model.dao.TodoModel;
import id.study.demo.common.model.dto.todos.TodoCreateRequestDTO;
import id.study.demo.common.model.dto.todos.TodoResponseDTO;
import id.study.demo.common.model.dto.todos.TodoUpdateRequestDTO;
import id.study.demo.common.utils.AssertUtil;
import id.study.demo.repository.TodoRepository;
import id.study.demo.services.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {
    private final TodoRepository todoRepository;
    private final TodoMapper todoMapper;

    @Override
    public TodoResponseDTO createTodo(TodoCreateRequestDTO requestDTO, String userId){
        TodoModel todo = todoMapper.toEntity(requestDTO);
        todo.setUserId(userId);

        TodoModel savedTodo = todoRepository.save(todo);
        return todoMapper.toResponseDTO(savedTodo);
    }

    @Override
    public Optional<TodoResponseDTO> findById(String id){
        return todoRepository.findById(id)
                .map(todoMapper::toResponseDTO);
    }
    @Override
    public Optional<TodoResponseDTO> findByUserId(String userId){
        return todoRepository.findByUserId(userId)
                .map(todoMapper::toResponseDTO);
    }

    @Override
    public Optional<TodoResponseDTO> findIncomplete(String todoId){
        return todoRepository.findByIdAndCompletedFalse(todoId)
                .map(todoMapper::toResponseDTO);
    }

    @Override
    public Optional<TodoResponseDTO> findCompleted(String todoId){
        return todoRepository.findByIdAndCompletedTrue(todoId)
                .map(todoMapper::toResponseDTO);
    }

    @Override
    public Optional<TodoResponseDTO> findTodo(String todoId){
        return todoRepository.findByIdAndDeletedFalse(todoId)
                .map(todoMapper::toResponseDTO);
    }

    @Transactional
    public void deleteTodo(String todoId){
        Optional<TodoModel> todoModel = todoRepository.findByIdAndDeletedFalse(todoId);

        if (todoModel.isPresent()) todoRepository.softDeleteById(todoId);
        else throw new NotFoundException("Task already deleted");
    }

    @Transactional
    public void completeTodo(String todoId){
        Optional<TodoModel> todoModel = todoRepository.findByIdAndCompletedFalse(todoId);

        if (todoModel.isPresent()) todoRepository.completedById(todoId);
        else throw new NotFoundException("Task already completed");
    }

    @Transactional
    public TodoResponseDTO updateTodo(TodoUpdateRequestDTO todoUpdateRequestDTO) {
        Optional<TodoModel> todoModel = todoRepository.findById(todoUpdateRequestDTO.getTodoId());
        if(todoModel.isEmpty()) throw new NotFoundException("Task not found");

        Optional<TodoModel> todoCompleted = todoRepository.findByIdAndCompletedTrue(todoUpdateRequestDTO.getTodoId());
        if(todoCompleted.isPresent()) throw new BadRequestException("Task alreadu completed");

        TodoModel todo = todoModel.get();

        todo.setTitle(todoUpdateRequestDTO.getTitle());
        todo.setDescription(todoUpdateRequestDTO.getDescription());
        todo.setUpdatedAt(LocalDateTime.now());

        TodoModel savedTodo = todoRepository.save(todo);
        return todoMapper.toResponseDTO(savedTodo);
    }
}
