package com.example.demo.controller;

import com.example.demo.model.Reservation;
import com.example.demo.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
public class ReservationController {

    @Autowired
    private ReservationRepository reservationRepo;

    @GetMapping("/reservation")
    public String showForm(Model model) {
        model.addAttribute("reservation", new Reservation());
        return "reservation_form";
    }

    @PostMapping("/reservation")
    public String submitForm(
        @Validated @ModelAttribute Reservation reservation,
        BindingResult result,
        Model model) {

        if (result.hasErrors()) {
            return "reservation_form";
        }

        reservationRepo.save(reservation);
        model.addAttribute("message", "Reservation submitted successfully!");
        return "reservation_form";
    }
}