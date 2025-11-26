package com.challenge.dto.reservation;

import com.challenge.model.Reservation;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReservationResponse {

    private Long id;

    private Long studentId;
    private Long canteenId;

    private LocalDate date;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime time;

    private int duration;

    private Reservation.Status status;

    public ReservationResponse() {}

    public ReservationResponse(Reservation r) {
        this.id = r.getId();
        this.studentId = r.getStudent().getId();
        this.canteenId = r.getCanteen().getId();
        this.date = r.getDate();
        this.time = r.getTime();
        this.duration = r.getDuration();
        this.status = r.getStatus();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getCanteenId() {
        return canteenId;
    }

    public void setCanteenId(Long canteenId) {
        this.canteenId = canteenId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Reservation.Status getStatus() {
        return status;
    }

    public void setStatus(Reservation.Status status) {
        this.status = status;
    }
}
