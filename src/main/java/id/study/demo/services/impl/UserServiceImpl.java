package id.study.demo.services.impl;

import id.study.demo.common.mapper.UserMapper;
import id.study.demo.common.model.dao.UserModel;
import id.study.demo.common.model.dto.users.UserRequestDTO;
import id.study.demo.common.model.dto.users.UserResponseDTO;
import id.study.demo.repository.UserRepository;
import id.study.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<UserResponseDTO> findUserByEmail(String email){
        return userRepository.findByEmail(email)
                .map(userMapper::toResponseDTO);
    }

    @Override
    public Optional<UserResponseDTO> findUserByUsername(String username){
        return userRepository.findByUsername(username)
                .map(userMapper::toResponseDTO);
    }

    @Override
    public UserResponseDTO registerUser(UserRequestDTO userRequestDTO){

        UserModel user = userMapper.toEntity(userRequestDTO);
        user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));

        UserModel savedUser = userRepository.save(user);
        return userMapper.toResponseDTO(savedUser);
    }
}
