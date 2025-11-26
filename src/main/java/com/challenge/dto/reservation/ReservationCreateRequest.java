package com.challenge.dto.reservation;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReservationCreateRequest {

    @NotNull(message = "Student ID is required.")
    private Long studentId;

    @NotNull(message = "Canteen ID is required.")
    private Long canteenId;

    @NotNull(message = "Date is required.")
    private LocalDate date;

    @NotNull(message = "Time is required.")
    private LocalTime time;

    @Min(value = 1, message = "Duration is required.")
    private int duration;

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }

    public Long getCanteenId() { return canteenId; }
    public void setCanteenId(Long canteenId) { this.canteenId = canteenId; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public LocalTime getTime() { return time; }
    public void setTime(LocalTime time) { this.time = time; }

    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }
}
