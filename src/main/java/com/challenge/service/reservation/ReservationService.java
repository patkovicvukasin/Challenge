package com.challenge.service.reservation;

import com.challenge.model.Reservation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ReservationService {

    Reservation createReservation(Long studentId, Long canteenId,
                                  LocalDate date, LocalTime time, int duration);

    Reservation cancelReservation(Long reservationId, Long studentId);

    Reservation getReservation(Long id);

    List<Reservation> getReservationsForStudent(Long studentId);
}
