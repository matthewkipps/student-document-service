package com.matthew.student_document_service;

import com.matthew.student_document_service.entity.User;
import com.matthew.student_document_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class StudentDocumentServiceApplication {
	@Autowired
	PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(StudentDocumentServiceApplication.class, args);
	}
	@Bean
	public CommandLineRunner initAdmin(UserRepository userRepository) {
		return args -> {
			String adminEmail = "admin@school.com";
			if (userRepository.findByEmail(adminEmail).isEmpty()) {
				User admin = new User();
				admin.setName("Admin");
				admin.setEmail(adminEmail);
				admin.setPassword(passwordEncoder.encode("admin123"));
				admin.setSchoolIdentifier("ADMIN");
				admin.setRole("ROLE_ADMIN");
				userRepository.save(admin);
				System.out.println("Admin created: " + adminEmail + " / admin123");
			}
		};
	}
}
