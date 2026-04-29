package com.matthew.student_document_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;
    private String role;
    private String schoolIdentifier;

    // Documents created by this user
    @OneToMany(mappedBy = "createdBy", fetch = FetchType.LAZY)
    private List<Document> createdDocuments;

    // Documents last edited by this user
    @OneToMany(mappedBy = "lastEditedBy", fetch = FetchType.LAZY)
    private List<Document> editedDocuments;
}