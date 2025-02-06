package com.kwhackathon.broom.participant.controller;

import com.kwhackathon.broom.participant.service.ParticipantService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
@AllArgsConstructor
public class ParticipantControllerImpl implements ParticipantController {

    private final ParticipantService participantService;

    // 해당 채팅방의 참여자 목록 조회
    @Override
    @GetMapping("/list/board/{boardId}")
    public ResponseEntity<?> getParticipantsByBoardId(@PathVariable String boardId,@RequestParam(defaultValue = "0") int page) {
        try{
            return ResponseEntity.status(HttpStatus.OK)
                    .body(participantService.getParticipantsByBoardId(boardId,page));
        }catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)//404
                    .body(e.getMessage());
        } catch(AccessDeniedException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN)//403
                    .body("채팅방 참여자만 조회할 수 있습니다.");
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)//500
                    .body("목록 조회에 실패했습니다.");
        }
    }

}
