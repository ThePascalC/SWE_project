package com.example.CommerceReservation.repository;

import com.example.CommerceReservation.domain.Cubicle;
import com.example.CommerceReservation.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface CubicleRepository extends JpaRepository<Cubicle, Long> {
    @Query(value = "SELECT * FROM Cubicle u WHERE u.reservation_cubicle_id NOT IN (SELECT r.reservation_cubicle_id FROM Reservation r WHERE (r.date_in BETWEEN CAST(:dateIn AS DATE) AND CAST(:dateOut AS DATE)) or (r.date_out BETWEEN CAST(:dateIn AS DATE) and CAST(:dateOut AS DATE)))", nativeQuery = true)
    public List<Cubicle> findAvailableCubicle(@Param("dateIn")Date dateIn, @Param("dateOut")Date dateOut);

    @Query(value = "SELECT * FROM cubicle WHERE cubicle.reservation_cubicle_id = :id", nativeQuery = true)
     public Cubicle getCubicleId(@Param("id")long id);

}
