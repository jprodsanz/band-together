package com.pablo.bands.services;

import com.pablo.bands.models.LoginUser;
import com.pablo.bands.models.User;
import com.pablo.bands.repositories.UserRepo;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo repo;

    // REGISTER METHOD
    public User register(User newUser, BindingResult result) {
        // check for pre-existing errors
        if (result.hasErrors()) {
            return null;
        }
        // is email already in use?
        Optional<User> user = repo.findByEmail(newUser.getEmail());

        if (user.isPresent()) {
            result.rejectValue("email", "matches", "Email already in use");
            return null;
        }
        // Check if password and confirmPass are the same
        if (!newUser.getPassword().equals(newUser.getConfirmPass())) {
            result.rejectValue("confirmPass", "matches", "Your passwords do not match");
            return null;
        }
        // salt bea
        String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
        newUser.setPassword(hashed);

        return repo.save(newUser);
    }

   // LOGIN METHOD
    public User login(LoginUser loginUser, BindingResult result) {
        // checks if user exists and entry form errors
        if(result.hasErrors()) {
            return null;
        }
        // check if user logging in exists
        Optional<User> user = repo.findByEmail(loginUser.getEmail());
        if(user.isEmpty()) {
            result.rejectValue("email", "matches", "User email is not found");
            return null;
        }
        User u = user.get();
        // check if the password given by the user matches the one that's stored in db
        if(!BCrypt.checkpw(loginUser.getPassword(), u.getPassword())) {
            result.rejectValue("password", "matches", "Password is incorrect");
            return null;
        }
        return u;
    }


    public User createOrUpdate(User u) {
        return repo.save(u);
    }

    public User getOne(Long id) {
        // i for instance
        Optional<User> i = repo.findById(id);

        if (i.isPresent()) {
            return i.get();
        } else {

            return null;
        }
    }

    public List<User> getAll() {
        return repo.findAll();
    }

    public List<User> getAllExcept(Long id){
        return repo.findByIdNot(id);
    }

    public boolean deleteOne(Long id) {
        Optional<User> i = repo.findById(id);
        if (i.isPresent()) {
            repo.delete(i.get());
            return true;
        } else {
            return false;
        }
    }
}
//    public User register(User u, BindingResult result) {
//        Optional<User> optUser = userRepo.findByEmail(u.getEmail());
//        if (optUser.isPresent()){
//            result.rejectValue("email", "Matches", "Email is already taken ");
//        }
//        if (!u.getConfirmPass().equals(u.getPassword())){
//            result.rejectValue("confirmPass", "Matches", "Passwords do not match");
//        }
//        if (!u.getConfirmPass().equals(u.getPassword())){
//        }
//        if (result.hasErrors()){
//            return null;
//        }
//        String hashPW = BCrypt.hashpw(u.getPassword(), BCrypt.gensalt());
//        u.setPassword(hashPW);
//        return userRepo.save(u);
//    }
//    public User login(UserLogin l, BindingResult result) {
//        Optional<User> optUser = userRepo.findByEmail(l.getEmail());
//        if (optUser.isEmpty()) {
//            result.rejectValue("email", "Matches", "User not found");
//            return null;
//        }
//        User user = optUser.get();
//        if (!BCrypt.checkpw(l.getPassword(),user.getPassword())) {
//            result.rejectValue("password", "Matches", "Invalid Password" );
//            return null;
//        }
//        return user;
//    }
//    public User findById(Long id) {
//        Optional<User> optUser = userRepo.findById(id);
//        if (optUser.isEmpty()) {
//            return null;
//        }
//        return optUser.get();
//    }
//
//    public List<User> getAll(){
//        return userRepo.findAll();
//    }

