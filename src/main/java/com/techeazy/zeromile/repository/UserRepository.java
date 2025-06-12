package com.techeazy.zeromile.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techeazy.zeromile.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
