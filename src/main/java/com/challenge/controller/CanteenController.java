package com.challenge.controller;

import com.challenge.dto.canteen.*;
import com.challenge.model.Canteen;
import com.challenge.service.canteen.CanteenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/canteens")
public class CanteenController {

    @Autowired
    private CanteenService canteenService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CanteenResponse createCanteen(
            @RequestHeader("studentId") Long adminId,
            @Valid @RequestBody CanteenCreateRequest request
    ) {
        Canteen c = new Canteen();
        c.setName(request.getName());
        c.setLocation(request.getLocation());
        c.setCapacity(request.getCapacity());
        c.setWorkingHours(
                request.getWorkingHours().stream()
                        .map(dto -> new Canteen.WorkingHour(dto.getMeal(), dto.getFrom(), dto.getTo()))
                        .collect(Collectors.toList())
        );

        return new CanteenResponse(canteenService.createCanteen(c, adminId));
    }

    @PutMapping("/{id}")
    public CanteenResponse updateCanteen(
            @PathVariable Long id,
            @RequestHeader("studentId") Long adminId,
            @Valid @RequestBody CanteenCreateRequest request
    ) {
        Canteen updated = new Canteen();
        updated.setName(request.getName());
        updated.setLocation(request.getLocation());
        updated.setCapacity(request.getCapacity());
        updated.setWorkingHours(
                request.getWorkingHours().stream()
                        .map(dto -> new Canteen.WorkingHour(dto.getMeal(), dto.getFrom(), dto.getTo()))
                        .collect(Collectors.toList())
        );

        return new CanteenResponse(canteenService.updateCanteen(id, updated, adminId));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCanteen(
            @PathVariable Long id,
            @RequestHeader("studentId") Long adminId
    ) {
        canteenService.deleteCanteen(id, adminId);
    }

    @GetMapping
    public List<CanteenResponse> getAll() {
        return canteenService.getAllCanteens().stream()
                .map(CanteenResponse::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CanteenResponse getById(@PathVariable Long id) {
        return new CanteenResponse(canteenService.getCanteenById(id));
    }

    @GetMapping("/{id}/status")
    public CanteenStatusResponse getStatusOne(
            @PathVariable Long id,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate,
            @RequestParam LocalTime startTime,
            @RequestParam LocalTime endTime,
            @RequestParam int duration
    ) {
        return canteenService.getStatusOne(id, startDate, endDate, startTime, endTime, duration);
    }


    @GetMapping("/status")
    public List<CanteenStatusResponse> getStatusAll(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate,
            @RequestParam LocalTime startTime,
            @RequestParam LocalTime endTime,
            @RequestParam int duration
    ) {
        return canteenService.getStatusAll(startDate, endDate, startTime, endTime, duration);
    }
}
