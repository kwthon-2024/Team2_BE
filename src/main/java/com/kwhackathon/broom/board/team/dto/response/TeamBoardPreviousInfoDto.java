package com.kwhackathon.broom.board.team.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class TeamBoardPreviousInfoDto {
    private Long teamBoardId;
    private String title;
    private String content;
    private LocalDate trainingDate;
    private String meetingPlace;
    private String meetingTime;
    private int personnel;
}
