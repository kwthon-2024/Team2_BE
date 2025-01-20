package com.kwhackathon.broom.user.controller;

import com.kwhackathon.broom.user.dto.request.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface UserOperations {
    @PostMapping("/signup")
    ResponseEntity<?> signup(@RequestBody SignupDto signupDto);

    @PostMapping("/validate-id")
    ResponseEntity<?> validateId(@RequestBody ValidateIdDto validateIdDto);

    @PostMapping("/validate-nickname")
    ResponseEntity<?> validateNickname(@RequestBody ValidateNicknameDto validateNicknameDto);
    
    @PostMapping("/reissue")
    ResponseEntity<?> reissue(HttpServletRequest request, HttpServletResponse response);

    @DeleteMapping("/exit")
    ResponseEntity<?> exit(HttpServletRequest request, HttpServletResponse response);

    @GetMapping("/mypage")
    ResponseEntity<?> getMypageInfo();

    @GetMapping("/mypage/info")
    ResponseEntity<?> getMyInfo();

    @PutMapping("/mypage/info")
    ResponseEntity<?> updateUserInfo(@RequestBody UpdateUserInfoDto updateUserInfoDto);

    @PostMapping("/mypage/password")
    ResponseEntity<?> updatePassword(@RequestBody UpdatePasswordDto updatePasswordDto);
}