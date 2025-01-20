package com.kwhackathon.broom.board.carpool.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class CarpoolBoardPreviousInfoDto {
    private Long carpoolBoardId;
    private String title;
    private String content;
    private LocalDate trainingDate;
    private String departPlace;
    private String departTime;
    private int personnel;
    private int price;
}
