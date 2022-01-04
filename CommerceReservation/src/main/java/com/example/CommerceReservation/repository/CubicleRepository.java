package com.example.CommerceReservation.repository;

import com.example.CommerceReservation.domain.Cubicle;
import com.example.CommerceReservation.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface CubicleRepository extends JpaRepository<Cubicle, Long> {
    @Query(value = "SELECT * FROM cubicle u WHERE u.reservation_cubicle_id NOT IN (SELECT r.reservation_cubicle_id FROM reservation r WHERE (r.date_in<=CAST(:dateIn AS DATE) and r.date_out>=CAST(:dateIn AS DATE)) or  (r.date_in<=CAST(:dateOut AS DATE) and r.date_out>=CAST(:dateOut AS DATE)) or(CAST(:dateIn as DATE)<= r.date_in and CAST(:dateOut as DATE) >= r.date_in ) or(CAST(:dateIn as DATE)<= r.date_out and CAST(:dateOut as DATE) >= r.date_out ))", nativeQuery = true)
    public List<Cubicle> findAvailableCubicle(@Param("dateIn")Date dateIn, @Param("dateOut")Date dateOut);

    @Query(value = "SELECT * FROM cubicle WHERE cubicle.reservation_cubicle_id = :id", nativeQuery = true)
     public Cubicle getCubicleId(@Param("id")long id);

}
