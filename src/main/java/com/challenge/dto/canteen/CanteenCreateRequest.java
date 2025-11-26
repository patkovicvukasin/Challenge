package com.challenge.dto.canteen;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class CanteenCreateRequest {

    @NotBlank(message = "Name is required.")
    private String name;

    @NotBlank(message = "Location is required.")
    private String location;

    @Min(value = 1, message = "Capacity must be greater than 0.")
    private int capacity;

    @NotEmpty(message = "Working hours are required.")
    @Valid
    private List<CanteenWorkingHourDTO> workingHours;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    public List<CanteenWorkingHourDTO> getWorkingHours() { return workingHours; }
    public void setWorkingHours(List<CanteenWorkingHourDTO> workingHours) { this.workingHours = workingHours; }
}
