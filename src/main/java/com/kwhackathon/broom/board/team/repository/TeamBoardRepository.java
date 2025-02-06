package com.kwhackathon.broom.board.team.repository;

import com.kwhackathon.broom.board.team.entity.TeamBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface TeamBoardRepository extends JpaRepository<TeamBoard, Long> {
    @Query("SELECT e FROM TeamBoard e ORDER BY e.createdAt DESC")
    List<TeamBoard> findAllOrderByCreatedAtDesc();

    List<TeamBoard> findByTitleContainingOrderByCreatedAtDesc(String title);

    List<TeamBoard> findByTrainingDateOrderByCreatedAtDesc(LocalDate trainingDate);

    List<TeamBoard> findByIsFullOrderByCreatedAtDesc(boolean full);
}
