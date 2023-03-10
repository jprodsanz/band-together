package com.pablo.loginregdos.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class UserLogin {
    @NotEmpty (message="Required")
    @Email(message="Invalid email address")
        private String email;

    @NotEmpty (message="Required")
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
