package id.study.demo.services;

import id.study.demo.common.model.dto.users.UserRequestDTO;

public interface UserService {
    void registerUser(UserRequestDTO userRequestDTO);
}
