package com.matthew.student_document_service.service;

import com.matthew.student_document_service.dto.request.CreateUserRequest;
import com.matthew.student_document_service.dto.response.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse create(CreateUserRequest request);

    UserResponse getById(Long id);

    List<UserResponse> getAll();

    void delete(Long id);
}