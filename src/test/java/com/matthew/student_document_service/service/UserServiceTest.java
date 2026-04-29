package com.matthew.student_document_service.service;

import com.matthew.student_document_service.dto.request.CreateUserRequest;
import com.matthew.student_document_service.dto.response.UserResponse;
import com.matthew.student_document_service.entity.User;
import com.matthew.student_document_service.mapper.UserMapper;
import com.matthew.student_document_service.mapper.UserMapper;
import com.matthew.student_document_service.repository.UserRepository;
import com.matthew.student_document_service.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private UserMapper userMapper;

    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userMapper = new UserMapper();
        userService = new UserServiceImpl(userRepository, passwordEncoder, userMapper);
    }

    @Test
    void createUser_shouldEncodePasswordAndSave() {
        // Arrange
        CreateUserRequest request = new CreateUserRequest();
        request.setName("Alice");
        request.setEmail("alice@example.com");
        request.setSchoolIdentifier("SCH123");
        request.setPassword("password");

        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setName(request.getName());
        savedUser.setEmail(request.getEmail());
        savedUser.setSchoolIdentifier(request.getSchoolIdentifier());

        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        UserResponse result = userService.create(request);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Alice");

        verify(passwordEncoder).encode("password");
        verify(userRepository).save(any(User.class));
    }
}