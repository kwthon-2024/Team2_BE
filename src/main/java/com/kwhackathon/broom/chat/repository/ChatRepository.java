package com.kwhackathon.broom.chat.repository;

import com.kwhackathon.broom.chat.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {
}
