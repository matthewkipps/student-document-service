package com.matthew.student_document_service.controller;

import com.matthew.student_document_service.dto.request.CreateDocumentRequest;
import com.matthew.student_document_service.dto.response.DocumentResponse;
import com.matthew.student_document_service.service.DocumentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping
    public ResponseEntity<DocumentResponse> create(@RequestBody CreateDocumentRequest request) {
        DocumentResponse response = documentService.create(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocumentResponse> update(
            @PathVariable Long id,
            @RequestBody CreateDocumentRequest request) {
        return ResponseEntity.ok(documentService.update(id, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(documentService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<DocumentResponse>> getAll() {
        return ResponseEntity.ok(documentService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        documentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}