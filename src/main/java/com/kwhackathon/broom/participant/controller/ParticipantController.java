package com.kwhackathon.broom.participant.controller;

import org.springframework.http.ResponseEntity;

public interface ParticipantController {

    // 해당 채팅방의 참여자 목록 조회
    ResponseEntity<?> getParticipantsByBoardId(String boardId,int page);

    // 해당 유저의 채팅방 목록 조회
    //ResponseEntity<?> getBoardByParticipantId(String participantId);
}
