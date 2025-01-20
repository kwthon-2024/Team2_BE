package com.kwhackathon.broom.board.carpool.repository;

import com.kwhackathon.broom.board.carpool.entity.CarpoolBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;



public interface CarpoolBoardRepository extends JpaRepository<CarpoolBoard, Long> {
//    @Query("SELECT e FROM CarpoolBoard e ORDER BY e.createdAt DESC")
//    List<CarpoolBoard> findAllOrderByCreatedAtDesc();
//
//    List<CarpoolBoard> findByTitleContainingOrderByCreatedAtDesc(String title);
//
//    List<CarpoolBoard> findByTrainingDateOrderByCreatedAtDesc(LocalDate trainingDate);
//
//    List<CarpoolBoard> findByDepartPlaceContainingOrderByCreatedAtDesc(String departPlace);
//
//    List<CarpoolBoard> findByIsFullOrderByCreatedAtDesc(boolean full);
}
