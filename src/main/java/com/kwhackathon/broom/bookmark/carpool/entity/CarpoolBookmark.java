package com.kwhackathon.broom.bookmark.carpool.entity;

import com.kwhackathon.broom.board.carpool.entity.CarpoolBoard;
import com.kwhackathon.broom.bookmark.entity.Bookmark;
import com.kwhackathon.broom.user.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class CarpoolBookmark extends Bookmark {
    // 로그인 아이디
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // 게시물 고유 식별자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carpool_board_id")
    private CarpoolBoard carpoolBoard;
}
