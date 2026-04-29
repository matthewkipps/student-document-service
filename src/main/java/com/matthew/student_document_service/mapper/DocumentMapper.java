package com.matthew.student_document_service.mapper;

import com.matthew.student_document_service.dto.request.CreateDocumentRequest;
import com.matthew.student_document_service.dto.response.DocumentResponse;
import com.matthew.student_document_service.entity.Document;
import com.matthew.student_document_service.entity.User;
import org.springframework.stereotype.Component;


@Component
public class DocumentMapper {

    public static Document toEntity(CreateDocumentRequest request, User user) {
        return Document.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .createdBy(user)
                .lastEditedBy(user)
                .build();
    }

    public static DocumentResponse toResponse(Document doc) {
        return DocumentResponse.builder()
                .id(doc.getId())
                .title(doc.getTitle())
                .content(doc.getContent())
                .createdById(doc.getCreatedBy().getId())
                .lastEditedById(doc.getLastEditedBy().getId())
                .createdAt(doc.getCreatedAt())
                .updatedAt(doc.getUpdatedAt())
                .build();
    }
}