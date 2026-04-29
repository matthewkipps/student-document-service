package com.matthew.student_document_service.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private String schoolIdentifier;

    public UserResponse(Long id, String name, String email, String schoolIdentifier) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.schoolIdentifier = schoolIdentifier;
    }

    public UserResponse() {}
}