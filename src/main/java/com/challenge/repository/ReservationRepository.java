package com.challenge.repository;

import com.challenge.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByStudentIdAndDate(Long studentId, LocalDate date);

    List<Reservation> findByCanteenIdAndDate(Long canteenId, LocalDate date);

    List<Reservation> findByCanteenId(Long canteenId);

    List<Reservation> findByStudentId(Long studentId);

}
