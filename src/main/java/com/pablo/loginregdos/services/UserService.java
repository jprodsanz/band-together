package com.pablo.loginregdos.services;

import com.pablo.loginregdos.models.User;
import com.pablo.loginregdos.models.UserLogin;
import com.pablo.loginregdos.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

    public User register(User u, BindingResult result) {
        if (!u.getConfirmPass().equals(u.getPassword())){
            result.rejectValue("confirmPasss", null, "Passwords do not match");
        }
        if (result.hasErrors()){
            return null;
        }
        return userRepo.save(u);
    }
    public User login(UserLogin l, BindingResult result) {
        Optional<User> optUser = userRepo.findByEmail(l.getEmail());
        if (optUser.isEmpty()) {
            return null;
        }
        User user = optUser.get();
        if (user.getPassword().equals(l.getPassword())) {
            return null;
        }
        return user;
    }
}
