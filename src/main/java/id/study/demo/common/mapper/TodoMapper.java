package id.study.demo.common.mapper;

import id.study.demo.common.model.dao.TodoModel;
import id.study.demo.common.model.dto.todos.TodoCreateRequestDTO;
import id.study.demo.common.model.dto.todos.TodoResponseDTO;
import id.study.demo.common.model.vo.todo.TodoView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface TodoMapper {

    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID().toString())")
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    TodoModel toEntity(TodoCreateRequestDTO dto);

    TodoResponseDTO toResponseDTO(TodoModel entity);

    @Mapping(target = "id", expression = "java(\"todo-\" + dto.getId())")
    @Mapping(target = "userId", expression = "java(\"user-\" + dto.getUserId())")
    TodoView toVO(TodoResponseDTO dto);
}