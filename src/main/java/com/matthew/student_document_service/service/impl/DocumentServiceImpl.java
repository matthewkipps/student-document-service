package com.matthew.student_document_service.service.impl;

import com.matthew.student_document_service.dto.request.CreateDocumentRequest;
import com.matthew.student_document_service.dto.response.DocumentResponse;
import com.matthew.student_document_service.entity.Document;
import com.matthew.student_document_service.entity.User;
import com.matthew.student_document_service.exception.ResourceNotFoundException;
import com.matthew.student_document_service.mapper.DocumentMapper;
import com.matthew.student_document_service.repository.DocumentRepository;
import com.matthew.student_document_service.repository.UserRepository;
import com.matthew.student_document_service.service.DocumentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final UserRepository userRepository;
    private final DocumentMapper documentMapper;

    public DocumentServiceImpl(DocumentRepository documentRepository,
                               UserRepository userRepository,
                               DocumentMapper documentMapper) {
        this.documentRepository = documentRepository;
        this.userRepository = userRepository;
        this.documentMapper = documentMapper;
    }

    @Override
    public DocumentResponse create(CreateDocumentRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + request.getUserId()));

        Document document = DocumentMapper.toEntity(request, user);

        return DocumentMapper.toResponse(documentRepository.save(document));
    }

    @Override
    public DocumentResponse update(Long documentId, CreateDocumentRequest request) {
        Document existing = documentRepository.findById(documentId)
                .orElseThrow(() -> new ResourceNotFoundException("Document not found with id: " + documentId));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + request.getUserId()));

        existing.setTitle(request.getTitle());
        existing.setContent(request.getContent());
        existing.setLastEditedBy(user);

        return DocumentMapper.toResponse(documentRepository.save(existing));
    }

    @Override
    public DocumentResponse getById(Long id) {
        Document doc = documentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Document not found with id: " + id));

        return DocumentMapper.toResponse(doc);
    }

    @Override
    public List<DocumentResponse> getAll() {
        return documentRepository.findAll()
                .stream()
                .map(DocumentMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (!documentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Document not found with id: " + id);
        }
        documentRepository.deleteById(id);
    }
}