package com.example.nicklastest.models.User;

import java.time.LocalDateTime;

public class UserRequest {
    private String Email;
    private String Password;
    private String FirstName;
    private String LastName;
    private double Height;
    private int GenderID;
    private LocalDateTime BirthdayDate;
    private int UserPlanID;

    public UserRequest(String email, String password, String firstName, String lastName, double height, int genderID, LocalDateTime birthdayDate, int userPlanID) {
        Email = email;
        Password = password;
        FirstName = firstName;
        LastName = lastName;
        Height = height;
        GenderID = genderID;
        BirthdayDate = birthdayDate;
        UserPlanID = userPlanID;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public double getHeight() {
        return Height;
    }

    public void setHeight(double height) {
        Height = height;
    }

    public int getGenderID() {
        return GenderID;
    }

    public void setGenderID(int genderID) {
        GenderID = genderID;
    }

    public LocalDateTime getBirthdayDate() {
        return BirthdayDate;
    }

    public void setBirthdayDate(LocalDateTime birthdayDate) {
        BirthdayDate = birthdayDate;
    }

    public int getUserPlanID() {
        return UserPlanID;
    }

    public void setUserPlanID(int userPlanID) {
        UserPlanID = userPlanID;
    }
}
