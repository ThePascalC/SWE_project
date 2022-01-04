package com.example.CommerceReservation.service;

import com.example.CommerceReservation.domain.CustomEmployeeDetails;
import com.example.CommerceReservation.domain.Employee;
import com.example.CommerceReservation.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomEmployeeDetailsService implements UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByEmail(email);
        if(employee == null){
            throw new UsernameNotFoundException("User not found!");
        }

        return new CustomEmployeeDetails(employee);
    }
}
