package com.challenge.service.reservation;

import com.challenge.exception.BadRequestException;
import com.challenge.exception.NotFoundException;
import com.challenge.model.Canteen;
import com.challenge.model.Reservation;
import com.challenge.model.Student;
import com.challenge.repository.CanteenRepository;
import com.challenge.repository.ReservationRepository;
import com.challenge.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CanteenRepository canteenRepository;

    @Override
    public Reservation createReservation(Long studentId, Long canteenId,
                                         LocalDate date, LocalTime time, int duration) {

        if (duration != 30 && duration != 60)
            throw new BadRequestException("Duration must be 30 or 60 min.");

        if (!(time.getMinute() == 0 || time.getMinute() == 30))
            throw new BadRequestException("Time must start at :00 or :30.");

        if (LocalDateTime.of(date, time).isBefore(LocalDateTime.now()))
            throw new BadRequestException("Cannot reserve time in the past.");

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException("Student not found."));

        Canteen canteen = canteenRepository.findById(canteenId)
                .orElseThrow(() -> new NotFoundException("Canteen not found."));

        List<Reservation> studentReservations =
                reservationRepository.findByStudentIdAndDate(studentId, date);

        for (Reservation r : studentReservations) {
            if (r.getStatus() == Reservation.Status.Active &&
                    r.overlapsWith(date, time, duration))
                throw new BadRequestException("Student already has reservation in this time.");
        }

        List<Reservation> canteenReservations =
                reservationRepository.findByCanteenIdAndDate(canteenId, date);

        long used = canteenReservations.stream()
                .filter(r -> r.getStatus() == Reservation.Status.Active)
                .filter(r -> r.overlapsWith(date, time, duration))
                .count();

        if (used >= canteen.getCapacity())
            throw new BadRequestException("No available seats in this canteen.");

        Reservation reservation = new Reservation();
        reservation.setStudent(student);
        reservation.setCanteen(canteen);
        reservation.setDate(date);
        reservation.setTime(time);
        reservation.setDuration(duration);
        reservation.setStatus(Reservation.Status.Active);

        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation cancelReservation(Long reservationId, Long studentId) {

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new NotFoundException("Reservation not found."));

        if (!reservation.getStudent().getId().equals(studentId))
            throw new BadRequestException("You can cancel only your own reservation.");

        reservation.setStatus(Reservation.Status.Cancelled);
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation getReservation(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Reservation not found."));
    }

    @Override
    public List<Reservation> getReservationsForStudent(Long studentId) {
        return reservationRepository.findByStudentId(studentId);
    }
}

