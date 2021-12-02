package com.example.CommerceReservation.service;

import com.example.CommerceReservation.domain.Cubicle;
import com.example.CommerceReservation.repository.CubicleRepository;
import com.example.CommerceReservation.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SearchService {
    private final ReservationRepository reservationRepository;
    private final CubicleRepository cubicleRepository;

    public SearchService(ReservationRepository reservationRepository, CubicleRepository cubicleRepository) {
        this.reservationRepository = reservationRepository;
        this.cubicleRepository = cubicleRepository;
    }

    public List<Cubicle> getAvailable(Date start, Date end){
        List<Cubicle> cubicles = cubicleRepository.findAvailableCubicle(start, end);
        return cubicles;
    }
}
