package com.softuni.projectForExam.techStore.models;

public class UserRegisterBindingModel {
    private String fullName;
    private String email;
    private String hunterCode;
    private String password;
    private String confirmPassword;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getHunterCode() {
        return hunterCode;
    }

    public void setHunterCode(String hunterCode) {
        this.hunterCode = hunterCode;
    }
}
