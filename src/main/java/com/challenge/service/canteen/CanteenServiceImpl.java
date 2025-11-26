package com.challenge.service.canteen;

import com.challenge.dto.canteen.CanteenStatusResponse;
import com.challenge.exception.BadRequestException;
import com.challenge.exception.NotFoundException;
import com.challenge.model.Canteen;
import com.challenge.model.Reservation;
import com.challenge.repository.CanteenRepository;
import com.challenge.repository.ReservationRepository;
import com.challenge.repository.StudentRepository;
import com.challenge.model.Student;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CanteenServiceImpl implements CanteenService {

    @Autowired
    private CanteenRepository canteenRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    private Student getAdmin(Long adminId) {
        Student admin = studentRepository.findById(adminId)
                .orElseThrow(() -> new NotFoundException("Admin not found."));
        if (!admin.isAdmin()) throw new BadRequestException("Student is not an admin.");
        return admin;
    }

    @Override
    public Canteen createCanteen(Canteen canteen, Long adminId) {
        getAdmin(adminId);
        return canteenRepository.save(canteen);
    }

    @Override
    public Canteen updateCanteen(Long id, Canteen updated, Long adminId) {
        getAdmin(adminId);

        Canteen existing = canteenRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Canteen not found."));

        existing.setName(updated.getName());
        existing.setLocation(updated.getLocation());
        existing.setCapacity(updated.getCapacity());
        existing.setWorkingHours(updated.getWorkingHours());

        return canteenRepository.save(existing);
    }

    @Override
    public void deleteCanteen(Long id, Long adminId) {
        getAdmin(adminId);

        Canteen canteen = canteenRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Canteen not found."));

        List<Reservation> reservations = reservationRepository.findByCanteenId(id);

        for (Reservation r : reservations) {
            r.setStatus(Reservation.Status.Cancelled);
        }
        reservationRepository.saveAll(reservations);

        canteenRepository.delete(canteen);
    }

    @Override
    public Canteen getCanteenById(Long id) {
        return canteenRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Canteen not found."));
    }

    @Override
    public List<Canteen> getAllCanteens() {
        return canteenRepository.findAll();
    }

    @Override
    public CanteenStatusResponse getStatusOne(Long canteenId,
                                              LocalDate startDate,
                                              LocalDate endDate,
                                              LocalTime startTime,
                                              LocalTime endTime,
                                              int duration) {

        Canteen c = getCanteenById(canteenId);

        List<CanteenStatusResponse.SlotDTO> slots = buildSlotsForCanteen(
                c, startDate, endDate, startTime, endTime, duration
        );

        return new CanteenStatusResponse(canteenId, slots);
    }

    @Override
    public List<CanteenStatusResponse> getStatusAll(LocalDate startDate,
                                                    LocalDate endDate,
                                                    LocalTime startTime,
                                                    LocalTime endTime,
                                                    int duration) {

        return canteenRepository.findAll().stream()
                .map(c -> new CanteenStatusResponse(
                        c.getId(),
                        buildSlotsForCanteen(c, startDate, endDate, startTime, endTime, duration)
                ))
                .collect(Collectors.toList());
    }

    private List<CanteenStatusResponse.SlotDTO> buildSlotsForCanteen(
            Canteen canteen,
            LocalDate startDate,
            LocalDate endDate,
            LocalTime startTime,
            LocalTime endTime,
            int duration) {

        List<CanteenStatusResponse.SlotDTO> result = new ArrayList<>();

        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {

            List<Reservation> reservations =
                    reservationRepository.findByCanteenIdAndDate(canteen.getId(), date);

            for (Canteen.WorkingHour wh : canteen.getWorkingHours()) {

                for (LocalTime slot = wh.getFrom();
                     !slot.plusMinutes(duration).isAfter(wh.getTo());
                     slot = slot.plusMinutes(30)) {

                    if (slot.isBefore(startTime) || slot.isAfter(endTime)) continue;

                    final LocalDate d = date;
                    final LocalTime t = slot;

                    long used = reservations.stream()
                            .filter(r -> r.getStatus() == Reservation.Status.Active)
                            .filter(r -> r.overlapsWith(d, t, duration))
                            .count();


                    int free = canteen.getCapacity() - (int) used;

                    result.add(new CanteenStatusResponse.SlotDTO(
                            date,
                            wh.getMeal().name(),
                            slot,
                            free
                    ));
                }
            }
        }

        return result;
    }
}

