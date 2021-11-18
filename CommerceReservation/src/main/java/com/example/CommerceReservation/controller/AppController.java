package com.example.CommerceReservation.controller;


import com.example.CommerceReservation.domain.Cubicle;
import com.example.CommerceReservation.domain.Employee;
import com.example.CommerceReservation.domain.SearchReservation;
import com.example.CommerceReservation.repository.CubicleRepository;
import com.example.CommerceReservation.repository.EmployeeRepository;
import com.example.CommerceReservation.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AppController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private CubicleRepository cubicleRepository;


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
        model.addAttribute("searchReservation", new SearchReservation());
        return "new_reservation";
    }

    @GetMapping("/view_reservations")
    public String view_reservations(){
        return "view_reservations";
    }


    @GetMapping("/list_users")
    public String viewUsersList(Model model){
        List<Employee> listUsers = employeeRepository.findAll();
        model.addAttribute("listUsers", listUsers);
        return "users";
    }


}
