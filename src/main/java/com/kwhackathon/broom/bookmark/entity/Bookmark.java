package com.kwhackathon.broom.bookmark.entity;

import com.kwhackathon.broom.board.entity.Board;
import com.kwhackathon.broom.user.entity.User;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Bookmark {

    // 게시물 북마크 식별자
    @Id
    @GeneratedValue
    @Column(name = "bookmark_id")
    private Long id;

}