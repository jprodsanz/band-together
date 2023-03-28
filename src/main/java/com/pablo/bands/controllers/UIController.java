package com.pablo.bands.controllers;

import com.pablo.bands.models.LoginUser;
import com.pablo.bands.models.User;
import com.pablo.bands.services.BandService;
import com.pablo.bands.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class UIController {
    @Autowired
    private UserService userService;
    @Autowired
    private BandService bandService;

    @GetMapping("/user/register")
    public String UserRegister(@ModelAttribute("user") User user) {
        return "register.jsp";
    }
    // Login render route
    @GetMapping("/user/login")
    public String UserLogin(@ModelAttribute("loginUser") LoginUser loginUser) {
        return "login.jsp";
    }
    // this is your dashboard

    @GetMapping("/")
    public String Dashboard(Model model, HttpSession session, RedirectAttributes redirect) {
        if(session.getAttribute("userId") == null) {
            redirect.addFlashAttribute("error", "You must login to access content");
            return "redirect:/user/login";
        }
        User u = userService.getOne((Long) session.getAttribute("userId"));
        System.out.println(u.getUsername());
            model.addAttribute("user", u);
            model.addAttribute("otherBands", bandService.getAllWithoutUser(u));
        return "index.jsp";
    }

}
