package com.matthew.student_document_service.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import com.matthew.student_document_service.dto.request.CreateUserRequest;
import com.matthew.student_document_service.dto.response.UserResponse;
import com.matthew.student_document_service.entity.User;
import com.matthew.student_document_service.exception.ResourceNotFoundException;
import com.matthew.student_document_service.mapper.UserMapper;
import com.matthew.student_document_service.repository.UserRepository;
import com.matthew.student_document_service.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }



    @Override
    public UserResponse create(CreateUserRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setSchoolIdentifier(request.getSchoolIdentifier());

        user.setPassword(passwordEncoder.encode(request.getPassword()));

        User saved = userRepository.save(user);
        return userMapper.toResponse(saved);
    }

    @Override
    public UserResponse getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        return UserMapper.toResponse(user);
    }

    @Override
    public List<UserResponse> getAll() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }
}