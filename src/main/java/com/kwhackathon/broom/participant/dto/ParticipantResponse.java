package com.kwhackathon.broom.participant.dto;

import com.kwhackathon.broom.participant.entity.Participant;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

public class ParticipantResponse {
    @Getter
    @AllArgsConstructor
    public static class ParticipantElement {
        private String userId;
        private String username;

        public ParticipantElement(Participant participant) {
            this.userId = participant.getUser().getUserId();
            this.username = participant.getUser().getUsername();
        }
    }

    @Getter
    @AllArgsConstructor
    public static class ParticipantList {
        private List<ParticipantElement> participants;
        private boolean hasNext;
    }
}
