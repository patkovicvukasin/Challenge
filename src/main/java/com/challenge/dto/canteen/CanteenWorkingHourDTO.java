package com.challenge.dto.canteen;

import com.challenge.model.Canteen.Meal;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public class CanteenWorkingHourDTO {

    @NotNull(message = "Meal is required.")
    private Meal meal;

    @NotNull(message = "Start time is required.")
    private LocalTime from;

    @NotNull(message = "End time is required.")
    private LocalTime to;

    public Meal getMeal() { return meal; }
    public void setMeal(Meal meal) { this.meal = meal; }

    public LocalTime getFrom() { return from; }
    public void setFrom(LocalTime from) { this.from = from; }

    public LocalTime getTo() { return to; }
    public void setTo(LocalTime to) { this.to = to; }
}
