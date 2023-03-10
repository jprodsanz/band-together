package com.pablo.loginregdos.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserLogin {
    @NotBlank
    @Email(message="Invalid email address")
        private String email;

    @NotBlank // don't give the min away
        private String password;

    public UserLogin() {};

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
