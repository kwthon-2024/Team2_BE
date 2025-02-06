package com.kwhackathon.broom.participant.service;

import com.kwhackathon.broom.participant.dto.ParticipantResponse;
import org.springframework.validation.annotation.Validated;

@Validated
public interface ParticipantService {

    // 해당 채팅방의 참여자 목록 조회
    ParticipantResponse.ParticipantList getParticipantsByBoardId(String boardId, int page);
    // 해당 유저의 채팅방 목록 조회 (게시판의 기능과 겹침)
    //BoardResponse.BoardList getBoardByParticipantId(String participantId);
}
