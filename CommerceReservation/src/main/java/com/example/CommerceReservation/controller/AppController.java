package com.example.CommerceReservation.controller;


import com.example.CommerceReservation.domain.*;
import com.example.CommerceReservation.repository.CubicleRepository;
import com.example.CommerceReservation.repository.EmployeeRepository;
import com.example.CommerceReservation.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.net.Authenticator;
import java.security.Principal;
import java.util.Date;
import java.util.List;

@Controller
public class AppController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private CubicleRepository cubicleRepository;

    Date startDate;
    Date endDate;

    @GetMapping("")
    public String viewHomePage(){
        return "login";
    }

    @GetMapping("/index")
    public String viewHome(){
        return "index";
    }

    @GetMapping("/account")
    public String changeAccount() { return "account";}

    @GetMapping("/register")
    public String showSignUpForm(Model model){
        model.addAttribute("employee", new Employee());
        return "signup_form";
    }

    @PostMapping("/process_register")
    public String processRegistration(Employee employee){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(employee.getPassword());
        employee.setPassword(encodedPassword);
        employeeRepository.save(employee);
        return "register_success";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/new_reservation")
    public String new_reservation(Model model){
        return "new_reservation";
    }

    @GetMapping("/view_reservations")
    public String view_reservations(Model model){
        CustomEmployeeDetails employee = (CustomEmployeeDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Reservation> userReservations = reservationRepository.getUserReservations(employee.getId());
        model.addAttribute("userReservations", userReservations);
        return "view_reservations";
    }


    @GetMapping("/list_users")
    public String viewUsersList(Model model){
        List<Employee> listUsers = employeeRepository.findAll();
        model.addAttribute("listUsers", listUsers);
        return "users";
    }
    @GetMapping("/findAvailable")
    public String viewAvailable(@RequestParam(value = "start") @DateTimeFormat(pattern = "yyyy-MM-dd") Date start, @RequestParam(value = "end") @DateTimeFormat(pattern = "yyyy-MM-dd") Date end, Model model){
        List<Cubicle> cubicles = cubicleRepository.findAvailableCubicle(start, end);
        startDate = start;
        endDate = end;
        model.addAttribute("cubicles", cubicles);
        model.addAttribute("reservation", new Reservation());
        return "available_cubicles";
    }

    @RequestMapping(value = "/reserve/{id}")
    public String processReservation(@PathVariable(name = "id") long id, Model model, Reservation reservation)
    {
        reservation.setCubicle(cubicleRepository.getCubicleId(id));
        reservation.setDateIn(startDate);
        reservation.setDateOut(endDate);
        CustomEmployeeDetails employee = (CustomEmployeeDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        reservation.setUserId(employee.getId());
        reservationRepository.save(reservation);
        List<Reservation> userReservations = reservationRepository.getUserReservations(employee.getId());
        model.addAttribute("userReservations", userReservations);
        return "reservation_success";
    }
    @RequestMapping(value = "/delete/{id}")
    public String deleteReservation(@PathVariable(name = "id") long id, Model model){
        reservationRepository.deleteUserReservation(id);
        CustomEmployeeDetails employee = (CustomEmployeeDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Reservation> userReservations = reservationRepository.getUserReservations(employee.getId());
        model.addAttribute("userReservations", userReservations);
        return "delete_success";
    }

}
