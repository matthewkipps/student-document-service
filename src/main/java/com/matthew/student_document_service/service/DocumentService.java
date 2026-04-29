package com.matthew.student_document_service.service;

import com.matthew.student_document_service.dto.request.CreateDocumentRequest;
import com.matthew.student_document_service.dto.response.DocumentResponse;

import java.util.List;

public interface DocumentService {

    DocumentResponse create(CreateDocumentRequest request);

    DocumentResponse update(Long documentId, CreateDocumentRequest request);

    DocumentResponse getById(Long id);

    List<DocumentResponse> getAll();

    void delete(Long id);
}