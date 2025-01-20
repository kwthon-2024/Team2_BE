package com.kwhackathon.broom.chat.carpool.entity;

import com.kwhackathon.broom.board.carpool.entity.CarpoolBoard;
import com.kwhackathon.broom.chat.entity.Chat;
import com.kwhackathon.broom.user.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class CarpoolChat extends Chat {

    // 채팅방 id
    @ManyToOne
    @JoinColumn(name = "carpool_board_id",nullable = false)
    private CarpoolBoard carpoolBoard;

}
