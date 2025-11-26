package com.challenge.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
public class Reservation {

    public enum Status {
        Active,
        Cancelled
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Student student;

    @ManyToOne(optional = false)
    private Canteen canteen;

    private LocalDate date;

    private LocalTime time;

    private int duration;

    @Enumerated(EnumType.STRING)
    private Status status = Status.Active;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Canteen getCanteen() {
        return canteen;
    }

    public void setCanteen(Canteen canteen) {
        this.canteen = canteen;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean overlapsWith(LocalDate date, LocalTime start, int duration) {
        if (!this.date.equals(date)) return false;

        LocalTime thisStart = this.time;
        LocalTime thisEnd = this.time.plusMinutes(this.duration);

        LocalTime otherStart = start;
        LocalTime otherEnd = start.plusMinutes(duration);

        return thisStart.isBefore(otherEnd) && otherStart.isBefore(thisEnd);
    }
}
