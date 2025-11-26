package com.challenge.dto.canteen;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class CanteenStatusResponse {

    private Long canteenId;
    private List<SlotDTO> slots;

    public CanteenStatusResponse() {}

    public CanteenStatusResponse(Long canteenId, List<SlotDTO> slots) {
        this.canteenId = canteenId;
        this.slots = slots;
    }

    public Long getCanteenId() {
        return canteenId;
    }

    public void setCanteenId(Long canteenId) {
        this.canteenId = canteenId;
    }

    public List<SlotDTO> getSlots() {
        return slots;
    }

    public void setSlots(List<SlotDTO> slots) {
        this.slots = slots;
    }

    public static class SlotDTO {

        private LocalDate date;
        private String meal;
        @JsonFormat(pattern = "HH:mm")
        private LocalTime startTime;
        private int remainingCapacity;

        public SlotDTO() {}

        public SlotDTO(LocalDate date, String meal, LocalTime startTime, int remainingCapacity) {
            this.date = date;
            this.meal = meal;
            this.startTime = startTime;
            this.remainingCapacity = remainingCapacity;
        }

        public LocalDate getDate() {
            return date;
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }

        public String getMeal() {
            return meal;
        }

        public void setMeal(String meal) {
            this.meal = meal;
        }

        public LocalTime getStartTime() {
            return startTime;
        }

        public void setStartTime(LocalTime startTime) {
            this.startTime = startTime;
        }

        public int getRemainingCapacity() {
            return remainingCapacity;
        }

        public void setRemainingCapacity(int remainingCapacity) {
            this.remainingCapacity = remainingCapacity;
        }
    }
}
