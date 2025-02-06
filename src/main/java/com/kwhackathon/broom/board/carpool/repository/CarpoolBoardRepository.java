package com.kwhackathon.broom.board.carpool.repository;


import com.kwhackathon.broom.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarpoolBoardRepository extends JpaRepository<Board, Long> {
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
