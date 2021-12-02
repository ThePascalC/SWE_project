package com.example.CommerceReservation.repository;

import com.example.CommerceReservation.domain.Cubicle;
import com.example.CommerceReservation.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query(value = "SELECT * FROM reservation WHERE reservation.reservation_employee_id = :id", nativeQuery = true)
    public List<Reservation> getUserReservations(@Param("id")long id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM reservation WHERE reservation.id = :id", nativeQuery = true)
    public void deleteUserReservation(@Param("id")long id);
}
