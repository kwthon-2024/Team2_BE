package com.kwhackathon.broom.user.service;

import java.time.LocalDate;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.kwhackathon.broom.common.util.JwtGenerator;
import com.kwhackathon.broom.common.util.JwtUtil;
import com.kwhackathon.broom.user.dto.UserRequest.ChangePasswordDto;
import com.kwhackathon.broom.user.dto.UserRequest.ChangeUserInfoDto;
import com.kwhackathon.broom.user.dto.UserRequest.SignupDto;
import com.kwhackathon.broom.user.dto.UserRequest.ValidateIdDto;
import com.kwhackathon.broom.user.dto.UserRequest.ValidateNicknameDto;
import com.kwhackathon.broom.user.dto.UserResponse.MypageInfoDto;
import com.kwhackathon.broom.user.dto.UserResponse.TokenDto;
import com.kwhackathon.broom.user.dto.UserResponse.UserCount;
import com.kwhackathon.broom.user.dto.UserResponse.UserInfoDto;
import com.kwhackathon.broom.user.entity.User;
import com.kwhackathon.broom.user.repository.UserRepository;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.Cookie;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Validated
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtGenerator jwtGenerator;
    private final JwtUtil jwtUtil;

    @Transactional
    public void createUser(@Valid SignupDto signupDto) {
        User user = signupDto.toEntity(passwordEncoder);
        if (userRepository.existsByUserId(user.getUserId()) || userRepository.existsByNickname(user.getNickname())) {
            throw new IllegalArgumentException("이미 존재하는 사용자입니다.");
        }
        
        userRepository.save(user);
    }

    public boolean validateId(@Valid ValidateIdDto dto) {
        return userRepository.existsByUserId(dto.getUserId());
    }

    public boolean validateNickname(@Valid ValidateNicknameDto dto) {
        return userRepository.existsByNickname(dto.getNickname());
    }

    public TokenDto reissue(Cookie[] cookies) {
        String refreshToken = validateRefresh(cookies);

        String userId = jwtUtil.getUserId(refreshToken);
        String role = jwtUtil.getRole(refreshToken);

        // 새 토큰 발급
        String newAccess = jwtGenerator.generateJwt("access", userId, role, 600000L);
        String newRefresh = jwtGenerator.generateJwt("refresh", userId, role, 86400000L);

        return new TokenDto(newAccess, newRefresh);
    }

    @Transactional
    public void deleteUser() throws NullPointerException, IllegalArgumentException {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        userRepository.deleteByUserId(userId);
    }

    @Override
    public User loadUserByUsername(String userId) throws UsernameNotFoundException {
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("사용자 정보를 찾을 수 없습니다."));
    }

    private String validateRefresh(Cookie[] cookies) throws NullPointerException, ExpiredJwtException,
            IllegalArgumentException {
        String refresh = null;
        if (cookies == null) {
            throw new NullPointerException("쿠키가 존재하지 않습니다");
        }

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("refresh")) {
                refresh = cookie.getValue();
            }
        }

        if (refresh == null) {
            throw new NullPointerException("토큰이 존재하지 않습니다");
        }

        try {
            jwtUtil.isExpired(refresh);
        } catch (ExpiredJwtException e) {
            throw new ExpiredJwtException(null, null, "refresh토큰이 만료되었습니다");
        }
        return refresh;
    }

    public MypageInfoDto getMypageInfo() {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = loadUserByUsername(userId);
        int reserveYear = LocalDate.now().getYear() - user.getDischargeYear();
        return new MypageInfoDto(user.getNickname(), reserveYear, user.getMilitaryBranch());
    }

    public UserInfoDto getUserInfo() {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = loadUserByUsername(userId);
        return new UserInfoDto(user);
    }

    @Transactional
    public void updateUserInfo(@Valid ChangeUserInfoDto dto) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = loadUserByUsername(userId);
        user.updateUserInfo(dto);
    }

    @Transactional
    public void updatePassword(@Valid ChangePasswordDto dto) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = loadUserByUsername(userId);
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("기존 비밀번호가 일치하지 않습니다");
        }
        user.updatePassword(passwordEncoder.encode(dto.getNewPassword()));
    }

    public UserCount getTotalUserCount() {
        return new UserCount(userRepository.countTotalUser());
    }
}
