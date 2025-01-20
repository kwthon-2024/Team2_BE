package com.kwhackathon.broom.bus.repository;

import com.kwhackathon.broom.bus.entity.BusReservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BusReservationRepository  extends JpaRepository<BusReservation, Long> {
    Optional<BusReservation> findByStudentId(String studentId);

    List<BusReservation> findAllByStudentId(String studentId);

    boolean existsByStudentId(String studentId);
}
