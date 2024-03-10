package org.example.repository;


import org.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    // Null 방지를 위한 Optional
    Optional<User> findByEmail(String email);

}
