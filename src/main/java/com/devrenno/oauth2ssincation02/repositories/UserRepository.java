package com.devrenno.oauth2ssincation02.repositories;

import com.devrenno.oauth2ssincation02.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
