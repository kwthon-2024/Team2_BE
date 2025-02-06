package com.kwhackathon.broom.board.carpool.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
public class WriteCarpoolBoardDto {
    private String title;
    private String content;
    private LocalDate trainingDate;
    private String departPlace;
    private LocalTime departTime;
    private int personnel;
    private int price;
}
