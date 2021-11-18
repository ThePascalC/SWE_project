package com.example.CommerceReservation.controller;

import com.example.CommerceReservation.domain.Cubicle;
import com.example.CommerceReservation.domain.SearchReservation;
import com.example.CommerceReservation.service.SearchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

   @GetMapping("/findAvailableCubicles")
    public String getAvailable(Model model){
        model.addAttribute("searchReservation", new SearchReservation());
        return "/new_reservation";
    }

    @PostMapping("/findAvailableCubicle")
    public String showAvailable(Model model, @ModelAttribute("searchReservation") SearchReservation searchReservation){
        List<Cubicle> cubicles = searchService.getAvailable(searchReservation.getStart(), searchReservation.getEnd());
        model.addAttribute("cubicles", cubicles);
        return "redirect:/findAvailableCubicles/";
    }


}
