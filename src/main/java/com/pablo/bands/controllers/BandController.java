package com.pablo.bands.controllers;

import com.pablo.bands.models.Band;
import com.pablo.bands.models.User;
import com.pablo.bands.services.BandService;
import com.pablo.bands.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class BandController {
    @Autowired
    private BandService service;
    @Autowired
    private UserService userService;

    @GetMapping("/band/create")
    public String CreateBand(HttpSession session, Model model) {
        if (session.getAttribute("userId") == null) {
            return "redirect:/user/login";
        }
        model.addAttribute("users", userService.getAllExcept((Long) session.getAttribute("userId")));
        model.addAttribute("band", new Band());
        return "create-band.jsp";
    }
    @GetMapping("/band/{id}")
    public String ViewBand(HttpSession session,
                           @PathVariable("id") Long id,
                           Model model
    ) {
        // check if user is logged in
        if(session.getAttribute("userId") == null) {
            return "redirect:/user/login";
        }
        // go get the specif band
        // load the band onto the page
        model.addAttribute("band", service.getOne(id));
        // return the page
        return "band.jsp";
    }

    @PostMapping("/band/create")
    public String CreateBand(
            @Valid @ModelAttribute("band") Band band, BindingResult result, Model model, HttpSession session
    ) {
        if (session.getAttribute("userId") == null) {
            return "redirect:/user/login";
        }

        // Link the bands on the dashboard to their own pages
        // display a list of the user's bands and a list of the band thee useer is not a part of
        if (result.hasErrors()) {
            model.addAttribute("users", userService.getAll());
            return "create-band.jsp";
        }
        band.getMembers().add(userService.getOne((Long) session.getAttribute("userId")));
        service.createOrUpdate(band);
        return "redirect:/";
    }
}
