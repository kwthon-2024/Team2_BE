package com.kwhackathon.broom.participant.service;

import com.kwhackathon.broom.participant.dto.ParticipantResponse;
import com.kwhackathon.broom.participant.entity.Participant;
import com.kwhackathon.broom.participant.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ParticipantServiceImpl implements ParticipantService {
    private final static int PAGE_SIZE = 15;
    private final ParticipantRepository participantRepository;

    // 해당 채팅방의 참여자 목록 조회
    @Override
    @PreAuthorize("@participantRepository.existsByUserUserIdAndBoardBoardId(authentication.name, #boardId)")
    // 현재 로그인한 사용자가 boardId의 참여자인지 확인한 후 메서드 실행
    // authentication.name → 현재 인증된 userId
    public ParticipantResponse.ParticipantList getParticipantsByBoardId(String boardId, int page) {
        Pageable pageable = PageRequest.of(page, PAGE_SIZE, Sort.by("createdAt").descending());

        Slice<Participant> slice = participantRepository.findSliceByBoardBoardId(pageable, boardId);

        List<ParticipantResponse.ParticipantElement> elements = slice.getContent().stream()
                .map(ParticipantResponse.ParticipantElement::new)
                .collect(Collectors.toList());

        return new ParticipantResponse.ParticipantList(elements, slice.hasNext());
    }
}
