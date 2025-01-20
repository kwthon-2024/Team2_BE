package com.kwhackathon.broom.user.repository;

import com.kwhackathon.broom.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUserId(String UserId);

    boolean existsByUserId(String userId);

    boolean existsByNickname(String nickname);

    void deleteByUserId(String userId);
}
