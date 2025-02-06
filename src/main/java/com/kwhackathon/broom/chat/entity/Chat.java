package com.kwhackathon.broom.chat.entity;

import com.kwhackathon.broom.participant.entity.Participant;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
public class Chat {
    @Id
    @GeneratedValue
    @Column(name = "chat_id")
    private Long id;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "massage")
    private String massage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="participant_id")
    private Participant participant;
}
