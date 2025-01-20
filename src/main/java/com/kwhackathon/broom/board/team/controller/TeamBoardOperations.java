package com.kwhackathon.broom.board.team.controller;

import com.kwhackathon.broom.board.team.dto.request.IsFullCheckDto;
import com.kwhackathon.broom.board.team.dto.request.WriteTeamBoardDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface TeamBoardOperations {
    @GetMapping("/view/team")
    ResponseEntity<?> getAllBoards();

    @GetMapping(value = "/view/team", params = { "category", "keyword" })
    ResponseEntity<?> searchBoard(@RequestParam String category, @RequestParam String keyword);

    @GetMapping("/view/team/{teamBoardId}")
    ResponseEntity<?> viewBoardDetail(@PathVariable Long teamBoardId);

    @GetMapping("/view/team/recruiting")
    ResponseEntity<?> getRecruitingBoard();

    @PostMapping("/team")
    ResponseEntity<?> createBoard(@RequestBody WriteTeamBoardDto writeTeamBoardDto);

    @GetMapping("/team/edit/{teamBoardId}")
    ResponseEntity<?> getIntialEditInfo(@PathVariable Long teamBoardId);

    @PutMapping("/team/edit/{teamBoardId}")
    ResponseEntity<?> editBoard(@PathVariable Long teamBoardId,
            @RequestBody WriteTeamBoardDto writeTeamBoardDto);

    @DeleteMapping("/team/{teamBoardId}")
    ResponseEntity<?> deleteBoard(@PathVariable Long teamBoardId);

    @GetMapping("/mypage/team")
    ResponseEntity<?> getMyBoard();

    @PutMapping("/team/check/{teamBoardId}")
    ResponseEntity<?> checkIsFull(@PathVariable Long teamBoardId, @RequestBody IsFullCheckDto isFullCheckDto);
}
