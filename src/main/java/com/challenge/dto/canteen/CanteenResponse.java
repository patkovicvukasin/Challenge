package com.challenge.dto.canteen;

import com.challenge.model.Canteen;

import java.util.List;
import java.util.stream.Collectors;

public class CanteenResponse {

    private Long id;
    private String name;
    private String location;
    private int capacity;

    private List<CanteenWorkingHourDTO> workingHours;

    public CanteenResponse() {}

    public CanteenResponse(Canteen c) {
        this.id = c.getId();
        this.name = c.getName();
        this.location = c.getLocation();
        this.capacity = c.getCapacity();

        this.workingHours = c.getWorkingHours()
                .stream()
                .map(w -> {
                    CanteenWorkingHourDTO dto = new CanteenWorkingHourDTO();
                    dto.setMeal(w.getMeal());
                    dto.setFrom(w.getFrom());
                    dto.setTo(w.getTo());
                    return dto;
                })
                .collect(Collectors.toList());
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

    public List<CanteenWorkingHourDTO> getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(List<CanteenWorkingHourDTO> workingHours) {
        this.workingHours = workingHours;
    }
}
