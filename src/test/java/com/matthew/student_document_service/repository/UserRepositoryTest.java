package com.matthew.student_document_service.repository;

import com.matthew.student_document_service.entity.User;
import com.matthew.student_document_service.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @TestConfiguration
    static class Config {
        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void findByEmail_shouldReturnUser() {
        User user = new User();
        user.setName("Alice");
        user.setEmail("alice@example.com");
        user.setPassword(passwordEncoder.encode("password"));
        user.setSchoolIdentifier("SCH001");

        userRepository.save(user);

        Optional<User> found = userRepository.findByEmail("alice@example.com");

        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("Alice");
    }
}