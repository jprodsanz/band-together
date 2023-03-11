package com.pablo.loginregdos.controllers;
import com.pablo.loginregdos.models.User;
import com.pablo.loginregdos.models.UserLogin;
import com.pablo.loginregdos.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    private UserService userServ;

    // Home route index
        @GetMapping("/")
        public String index(Model model ) {
            model.addAttribute("user", new User());
            model.addAttribute("userLogin", new UserLogin());
            return "login.jsp";
        }
    // Login render route
        @GetMapping("/login")
        public String getLogin(@ModelAttribute("userLogin") UserLogin userLogin) {
            return "login.jsp";
        }
    // Login post route
        @PostMapping("/login")
        public String login(@Valid @ModelAttribute("userLogin") UserLogin userLogin, BindingResult result, Model model, HttpSession session) {
            // run login method
            User user = userServ.login(userLogin, result);
            if(user == null) {
                model.addAttribute("user", new User());
                return "login.jsp";
            }
            // add user to DB
            // log user in
            session.setAttribute("userId", user.getId());
            return "redirect:/dashboard";
        }
//        Reg  render route
            @GetMapping("/register")
            public String getRegister(@ModelAttribute("user") User newUser) {
                return "register.jsp";
            }
    // Reg post
        @PostMapping("/register")
        public String register(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, HttpSession session) {
            // run reg method
            User newUser = userServ.register(user, result);
            if(newUser == null) {
                model.addAttribute("userLogin", new UserLogin());
                return "login.jsp";
            }
            // add user to DB
            // log user in
            session.setAttribute("userId", newUser.getId());
            return "redirect:/dashboard";
        }
    // Dashboard route
    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {
        Long id = (Long) session.getAttribute("userId");
        if (id==null) {
            return "redirect:/";
        }
        else {
            List<User> allUsers = userServ.getAll();
                User loggedUser = userServ.findById(id);
                model.addAttribute("user", loggedUser);
                model.addAttribute("allUsers", allUsers);
            return "dashboard.jsp";
        }
    }
        // Logout route
        @GetMapping("/logout")
        public String logout(HttpSession session) {
            session.setAttribute("userId", null);
            return "redirect:/";
        }
}
