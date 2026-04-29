package com.matthew.student_document_service.service;

import com.matthew.student_document_service.dto.request.CreateDocumentRequest;
import com.matthew.student_document_service.dto.response.DocumentResponse;
import com.matthew.student_document_service.entity.Document;
import com.matthew.student_document_service.entity.User;
import com.matthew.student_document_service.mapper.DocumentMapper;
import com.matthew.student_document_service.repository.DocumentRepository;
import com.matthew.student_document_service.repository.UserRepository;
import com.matthew.student_document_service.service.impl.DocumentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class DocumentServiceTest {

    @Mock
    private DocumentRepository documentRepository;

    @Mock
    private UserRepository userRepository;

    private DocumentMapper documentMapper;

    private DocumentServiceImpl documentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Use real mapper to avoid mocking final methods
        documentMapper = new DocumentMapper();

        // Inject mocks and real mapper into the service
        documentService = new DocumentServiceImpl(documentRepository, userRepository, documentMapper);
    }

    @Test
    void createDocument_shouldSaveDocument() {
        // Arrange
        CreateDocumentRequest request = new CreateDocumentRequest();
        request.setTitle("Test Doc");
        request.setContent("Content");
        request.setUserId(1L);

        User user = new User();
        user.setId(1L);
        user.setName("Alice");

        Document document = new Document();
        document.setId(1L);
        document.setTitle("Test Doc");
        document.setContent("Content");
        document.setCreatedBy(user);
        document.setLastEditedBy(user); // avoid NPE

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(documentRepository.save(any(Document.class))).thenReturn(document);

        // Act
        DocumentResponse response = documentService.create(request);

        // Assert
        assertThat(response).isNotNull();
        assertThat(response.getTitle()).isEqualTo("Test Doc");
        assertThat(response.getCreatedById()).isEqualTo(user.getId());
        assertThat(response.getLastEditedById()).isEqualTo(user.getId());

        // Verify repository interactions
        verify(userRepository).findById(1L);
        verify(documentRepository).save(any(Document.class));
    }
}