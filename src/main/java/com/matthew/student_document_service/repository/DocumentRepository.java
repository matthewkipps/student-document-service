package com.matthew.student_document_service.repository;

import com.matthew.student_document_service.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Long> {

    List<Document> findByCreatedById(Long userId);

    List<Document> findByLastEditedById(Long userId);
}