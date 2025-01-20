package com.kwhackathon.broom.participant.entity;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Participant {
    @GeneratedValue
    @Id
    @Column(name = "participant_id")
    private Long id;
}
