package com.matthew.student_document_service.mapper;

import com.matthew.student_document_service.dto.request.CreateUserRequest;
import com.matthew.student_document_service.dto.response.UserResponse;
import com.matthew.student_document_service.entity.User;
import org.springframework.stereotype.Component;


@Component
public class UserMapper {

    public static User toEntity(CreateUserRequest request) {
        return User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .schoolIdentifier(request.getSchoolIdentifier())
                .build();
    }

    public static UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .schoolIdentifier(user.getSchoolIdentifier())
                .build();
    }
}