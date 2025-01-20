package com.kwhackathon.broom.board.team.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TeamBoardListDto {
    List<TeamBoardListElement> result;
}
