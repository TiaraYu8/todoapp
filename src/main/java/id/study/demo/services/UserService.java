package id.study.demo.services;

import id.study.demo.common.model.dto.users.UserRequestDTO;
import id.study.demo.common.model.dto.users.UserResponseDTO;

import java.util.Optional;

public interface UserService {
    Optional<UserResponseDTO> findUserByEmail(String email);
    Optional<UserResponseDTO> findUserByUsername(String username);
    Optional<UserResponseDTO> findUserById(String id);
    UserResponseDTO registerUser(UserRequestDTO userRequestDTO);
}
