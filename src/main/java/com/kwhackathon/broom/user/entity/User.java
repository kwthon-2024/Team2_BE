package com.kwhackathon.broom.user.entity;

import com.kwhackathon.broom.board.team.entity.TeamBoard;
import com.kwhackathon.broom.bookmark.carpool.entity.CarpoolBookmark;
import com.kwhackathon.broom.user.dto.request.UpdateUserInfoDto;
import com.kwhackathon.broom.user.util.MilitaryChaplain;
import com.kwhackathon.broom.user.util.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    @Id
    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "discharge_year", nullable = false)
    private int dischargeYear; // 전역 연도

    @Column(name = "military_chaplain", nullable = false)
    @Enumerated(EnumType.STRING)
    private MilitaryChaplain militaryChaplain; // 군구분

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role; // 권한 정보

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<CarpoolBookmark> carpoolBookmarks;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<TeamBoard> teamBoards;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new SimpleGrantedAuthority(role.toString()));
        return collection;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userId;
    }

    public void updateUserInfo(UpdateUserInfoDto dto) {
        this.nickname = dto.getNickname();
        this.dischargeYear = dto.getDischargeYear();
        this.militaryChaplain = dto.getMilitaryChaplain();
    }

    public void updatePassword(String newPassword) {
        this.password = newPassword;
    }
}
