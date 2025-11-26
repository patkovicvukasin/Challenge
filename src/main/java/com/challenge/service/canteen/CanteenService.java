package com.challenge.service.canteen;

import com.challenge.model.Canteen;
import com.challenge.dto.canteen.CanteenStatusResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public interface CanteenService {

    Canteen createCanteen(Canteen canteen, Long adminId);

    Canteen updateCanteen(Long id, Canteen updated, Long adminId);

    void deleteCanteen(Long id, Long adminId);

    Canteen getCanteenById(Long id);

    List<Canteen> getAllCanteens();

    List<CanteenStatusResponse> getStatusAll(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime, int duration);

    CanteenStatusResponse getStatusOne(Long canteenId, LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime, int duration);
}
