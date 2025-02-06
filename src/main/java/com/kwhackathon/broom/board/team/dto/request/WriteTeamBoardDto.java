package com.kwhackathon.broom.board.team.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
public class WriteTeamBoardDto {
    private String title;
    private String content;
    private LocalDate trainingDate;
    private String meetingPlace;
    private LocalTime meetingTime;
    private int personnel;
}
