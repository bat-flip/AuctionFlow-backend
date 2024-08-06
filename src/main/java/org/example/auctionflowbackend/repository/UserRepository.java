package org.example.auctionflowbackend.repository;

import org.example.auctionflowbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);
}