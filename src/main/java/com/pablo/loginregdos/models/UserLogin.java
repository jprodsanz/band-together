package com.pablo.loginregdos.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class UserLogin {
    @NotEmpty (message="Your email is required")
    @Email(message="Invalid email address")
        private String email;

    @NotEmpty (message="Please enter your passsword")
        private String password;

    public UserLogin() {}

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
