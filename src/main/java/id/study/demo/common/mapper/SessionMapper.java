package id.study.demo.common.mapper;

import id.study.demo.common.model.dao.SessionModel;
import id.study.demo.common.model.dto.session.SessionResponseDTO;
import id.study.demo.common.model.vo.session.SessionView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SessionMapper {
    SessionResponseDTO toResponseDTO(SessionModel entity);

    @Mapping(target = "userId", expression = "java(\"user-\" + dto.getId())")
    SessionView toVO(SessionResponseDTO dto);
}
