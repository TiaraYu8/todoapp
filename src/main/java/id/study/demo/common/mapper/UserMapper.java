package id.study.demo.common.mapper;

import id.study.demo.common.model.dao.UserModel;
import id.study.demo.common.model.dto.users.UserRequestDTO;
import id.study.demo.common.model.dto.users.UserResponseDTO;
import id.study.demo.common.model.vo.users.UserView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID().toString())")
    @Mapping(target = "createdAt", ignore = true)
    UserModel toEntity(UserRequestDTO dto);

    UserResponseDTO toResponseDTO(UserModel entity);

    @Mapping(target = "id", expression = "java(\"user-\" + dto.getId())")
    UserView toVO(UserResponseDTO dto);
}