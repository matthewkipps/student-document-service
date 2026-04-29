package com.matthew.student_document_service.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class DocumentResponse {
    private Long id;
    private String title;
    private String content;

    private Long createdById;
    private Long lastEditedById;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public DocumentResponse(Long id, String title, String content,
                            Long createdById, Long lastEditedById,
                            LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdById = createdById;
        this.lastEditedById = lastEditedById;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public DocumentResponse() {}
}