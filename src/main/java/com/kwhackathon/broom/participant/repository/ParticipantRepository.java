package com.kwhackathon.broom.participant.repository;

import com.kwhackathon.broom.participant.entity.Participant;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    Slice<Participant> findSliceByBoardBoardId(Pageable pageable, String boardId);

    boolean existsByUserUserIdAndBoardBoardId(String userId, String boardId);

}

