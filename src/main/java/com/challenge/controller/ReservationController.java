package com.challenge.controller;

import com.challenge.dto.reservation.ReservationCreateRequest;
import com.challenge.dto.reservation.ReservationResponse;
import com.challenge.model.Reservation;
import com.challenge.service.reservation.ReservationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReservationResponse create(@Valid @RequestBody ReservationCreateRequest request) {

        Reservation r = reservationService.createReservation(
                request.getStudentId(),
                request.getCanteenId(),
                request.getDate(),
                request.getTime(),
                request.getDuration()
        );

        return new ReservationResponse(r);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReservationResponse cancel(
            @PathVariable Long id,
            @RequestParam Long studentId
    ) {
        Reservation r = reservationService.cancelReservation(id, studentId);
        return new ReservationResponse(r);
    }

    @GetMapping("/{id}")
    public ReservationResponse getById(@PathVariable Long id) {
        return new ReservationResponse(reservationService.getReservation(id));
    }

    @GetMapping("/students/{studentId}")
    public List<ReservationResponse> getForStudent(@PathVariable Long studentId) {
        return reservationService.getReservationsForStudent(studentId)
                .stream()
                .map(ReservationResponse::new)
                .collect(Collectors.toList());
    }
}
