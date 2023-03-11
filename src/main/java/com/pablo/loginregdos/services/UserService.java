package com.pablo.loginregdos.services;

import com.pablo.loginregdos.models.User;
import com.pablo.loginregdos.models.UserLogin;
import com.pablo.loginregdos.repositories.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

    public User register(User u, BindingResult result) {
        if (!u.getConfirmPass().equals(u.getPassword())){
            result.rejectValue("email", "Matches", "Email is already taken ");
        }
        if (!u.getConfirmPass().equals(u.getPassword())){
            result.rejectValue("confirmPass", null, "Passwords do not match");
        }
        if (result.hasErrors()){
            return null;
        }
        String hashPW = BCrypt.hashpw(u.getPassword(), BCrypt.gensalt());
        u.setPassword(hashPW);
        return userRepo.save(u);
    }
    public User login(UserLogin l, BindingResult result) {
        Optional<User> optUser = userRepo.findByEmail(l.getEmail());
        if (optUser.isEmpty()) {
            result.rejectValue("email", "Matches", "User not found");
            return null;
        }
        User user = optUser.get();
        if (!BCrypt.checkpw(l.getPassword(),user.getPassword())) {
            result.rejectValue("password", "Matches", "Invalid Password" );
            return null;
        }
        return user;
    }
    public User findById(Long id) {
        Optional<User> optUser = userRepo.findById(id);
        if (optUser.isEmpty()) {
            return null;
        }
        return optUser.get();
    }

    public List<User> getAll(){
        return userRepo.findAll();
    }
}
