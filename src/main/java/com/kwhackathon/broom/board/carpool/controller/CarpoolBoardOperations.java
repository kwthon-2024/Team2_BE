package com.kwhackathon.broom.board.carpool.controller;

import com.kwhackathon.broom.board.carpool.dto.request.WriteCarpoolBoardDto;
import com.kwhackathon.broom.board.carpool.dto.request.IsFullCheckDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface CarpoolBoardOperations {
    @GetMapping("/view/carpool")
    ResponseEntity<?> getAllBoards();

    @GetMapping(value = "/view/carpool", params = { "category", "keyword" })
    ResponseEntity<?> searchBoard(@RequestParam String category, @RequestParam String keyword);

    @GetMapping("/view/carpool/{carpoolBoardId}")
    ResponseEntity<?> viewBoardDetail(@PathVariable Long carpoolBoardId);

    @GetMapping("/view/carpool/recruiting")
    ResponseEntity<?> getRecruitingBoard();

    @PostMapping("/carpool")
    ResponseEntity<?> createBoard(@RequestBody WriteCarpoolBoardDto writeCarpoolBoardDto);

    @GetMapping("/carpool/edit/{carpoolBoardId}")
    ResponseEntity<?> getIntialEditInfo(@PathVariable Long carpoolBoardId);

    @PutMapping("/carpool/edit/{carpoolBoardId}")
    ResponseEntity<?> editBoard(@PathVariable Long carpoolBoardId,
            @RequestBody WriteCarpoolBoardDto writeCarpoolBoardDto);

    @DeleteMapping("/carpool/{carpoolBoardId}")
    ResponseEntity<?> deleteBoard(@PathVariable Long carpoolBoardId);

    @GetMapping("/mypage/carpool")
    ResponseEntity<?> getMyBoard();

    @PutMapping("/carpool/check/{carpoolBoardId}")
    ResponseEntity<?> checkIsFull(@PathVariable Long carpoolBoardId, @RequestBody IsFullCheckDto isFullCheckDto);
}
