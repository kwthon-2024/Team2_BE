package com.kwhackathon.broom.board.carpool.entity;

import com.kwhackathon.broom.board.entity.Board;
import com.kwhackathon.broom.bookmark.carpool.entity.CarpoolBookmark;
import com.kwhackathon.broom.chat.carpool.entity.CarpoolChat;
import com.kwhackathon.broom.participant.carpool.entity.CarpoolParticipant;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
//@DiscriminatorValue("carpool")
@Data
public class CarpoolBoard extends Board {

    // 금액
    @Column(name = "price", nullable = false)
    private int price;

    @OneToMany(mappedBy = "carpoolBoard", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarpoolBookmark> carpoolBookmarks = new ArrayList<>();

    @OneToMany(mappedBy = "carpoolBoard", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarpoolParticipant> carpoolParticipants = new ArrayList<>();

    @OneToMany(mappedBy = "carpoolBoard", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarpoolChat> carpoolChats = new ArrayList<>();

}
