package com.example.LibraryManagementSystem.Repository;

import com.example.LibraryManagementSystem.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User , Long> {
   Optional<User> findByEmail(String email);  // Changed from findByUsername to findByEmail
}
