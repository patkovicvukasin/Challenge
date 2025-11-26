package com.challenge.model;

import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.List;

@Entity
public class Canteen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    private String location;

    private int capacity;

    @ElementCollection
    @CollectionTable(name = "canteen_working_hours", joinColumns = @JoinColumn(name = "canteen_id"))
    private List<WorkingHour> workingHours;

    public enum Meal {
        breakfast,
        lunch,
        dinner
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<WorkingHour> getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(List<WorkingHour> workingHours) {
        this.workingHours = workingHours;
    }

    @Embeddable
    public static class WorkingHour {

        @Enumerated(EnumType.STRING)
        private Meal meal;
        @Column(name = "start_time", nullable = false)
        private LocalTime from;
        @Column(name = "end_time", nullable = false)
        private LocalTime to;

        public WorkingHour() {}

        public WorkingHour(Meal meal, LocalTime from, LocalTime to) {
            this.meal = meal;
            this.from = from;
            this.to = to;
        }
        public Meal getMeal() {
            return meal;
        }

        public void setMeal(Meal meal) {
            this.meal = meal;
        }

        public LocalTime getFrom() {
            return from;
        }

        public void setFrom(LocalTime from) {
            this.from = from;
        }

        public LocalTime getTo() {
            return to;
        }

        public void setTo(LocalTime to) {
            this.to = to;
        }
    }
}
