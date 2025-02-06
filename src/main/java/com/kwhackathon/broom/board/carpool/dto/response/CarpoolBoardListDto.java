package com.kwhackathon.broom.board.carpool.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CarpoolBoardListDto {
    List<CarpoolBoardListElement> result;
}
