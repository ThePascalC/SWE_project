package com.example.CommerceReservation.repository;

import com.example.CommerceReservation.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    @Query("SELECT u FROM Employee u WHERE u.email = ?1")
    public Employee findByEmail(String email);

}
