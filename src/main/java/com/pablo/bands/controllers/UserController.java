package com.pablo.bands.controllers;

import com.pablo.bands.models.LoginUser;
import com.pablo.bands.models.User;
import com.pablo.bands.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping("/user/register")
    public String UserRegister(
            @Valid @ModelAttribute("user") User user,
            BindingResult result,
            HttpSession session) {
        User newUser = service.register(user, result);
        if (newUser == null) {
            return "register.jsp";
        } else {
            session.setAttribute("userId", newUser.getId());
            return "redirect:/";
        }
    }
    @PostMapping("/user/login")
    public String UserLogin(
            @Valid @ModelAttribute("loginUser")LoginUser loginUser,
            BindingResult result, Model model,
            HttpSession session
            ) {
        // call the login service method
        User user = service.login(loginUser, result);
        // If invalid user, then reload the page
        if(user == null) {
            // Load the User Model into the model for the login/registration pages
            model.addAttribute("user", new User());
            return "login.jsp";
        }
        // If valid user then store user in session
        session.setAttribute("userId", user.getId());
        // return a redirect to the dashboard route
        return "redirect:/";

    }
    @GetMapping("/user/logout")
    public String UserLogout(HttpSession session) {
        session.invalidate();

        // user login should take you to the login page which you don't have set up
        return "redirect:/user/login";

    }
}
