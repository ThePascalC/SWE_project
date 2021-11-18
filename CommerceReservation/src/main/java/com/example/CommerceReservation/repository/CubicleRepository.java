package com.example.CommerceReservation.repository;

import com.example.CommerceReservation.domain.Cubicle;
import com.example.CommerceReservation.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface CubicleRepository extends JpaRepository<Cubicle, Long> {
    @Query(value = "SELECT * FROM Cubicle u WHERE u.reservation_cubicle_id NOT IN (SELECT r.reservation_cubicle_id FROM Reservation r WHERE r.dateIn = :dateIn AND r.dateOut = :dateOut)", nativeQuery = true)
    public List<Cubicle> findAvailableCubicle(@Param("dateIn")Date dateIn, @Param("dateOut")Date dateOut);


}
