package id.study.demo.services;

import id.study.demo.common.model.dto.users.UserRequestDTO;
import id.study.demo.common.model.dto.users.UserResponseDTO;

public interface UserService {
    UserResponseDTO findUser(String email);
    UserResponseDTO registerUser(UserRequestDTO userRequestDTO);
}
