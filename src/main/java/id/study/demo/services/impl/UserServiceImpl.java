package id.study.demo.services.impl;

import id.study.demo.common.exception.NotFoundException;
import id.study.demo.common.mapper.UserMapper;
import id.study.demo.common.model.dao.UserModel;
import id.study.demo.common.model.dto.users.UserRequestDTO;
import id.study.demo.common.model.dto.users.UserResponseDTO;
import id.study.demo.common.utils.AssertUtil;
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
    public UserResponseDTO findUser(String email){
        UserModel user = userRepository.findByEmail(email).
                orElseThrow(() -> new NotFoundException("User Not Found"));

        return userMapper.toResponseDTO(user);
    }

    @Override
    public UserResponseDTO registerUser(UserRequestDTO userRequestDTO){
        AssertUtil.isTrue(
                userRepository.findByEmail(userRequestDTO.getEmail()).isEmpty(),
                "Email already in use"
        );
        AssertUtil.isTrue(
                userRepository.findByUsername(userRequestDTO.getUsername()).isEmpty(),
                "Username already in use"
        );

        UserModel user = userMapper.toEntity(userRequestDTO);
        user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));

        UserModel savedUser = userRepository.save(user);
        return userMapper.toResponseDTO(savedUser);
    }
}
