package com.kwhackathon.broom.board.entity;

import com.kwhackathon.broom.user.entity.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
//@DiscriminatorColumn
@EntityListeners(AuditingEntityListener.class)
public class Board {
    @EqualsAndHashCode.Include
    @Id
    @Column(name = "board_id", updatable = false, nullable = false)
    private String id = UUID.randomUUID().toString(); // UUID로 고유 ID 생성

    // 게시물 제목
    @Column(name = "title", nullable = false)
    private String title;

    // 게시물 본문
    @Column(name = "content")
    private String content;

    // 생성 날짜
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // 사용자 지정 시간
    @Column(name = "time", nullable = false)
    private LocalTime time;

    // 사용자 지정 장소
    @Column(name = "place", nullable = false)
    private String place;

    // 인원
    @Column(name = "personnel", nullable = false)
    private int personnel;

    // 모집 완료 여부
    @Column(name = "is_full", nullable = false)
    private boolean isFull;

    // 훈련 날짜
    @Column(name = "training_date", nullable = false)
    private LocalDate trainingDate;

    // 부모테이블 간의 매핑은 상속구조 복잡하게하고 유지보수성 저하 -> 자식 테이블로

}
