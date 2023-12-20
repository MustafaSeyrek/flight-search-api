package com.seyrek.flightsearchapi.repositories;

import com.seyrek.flightsearchapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
