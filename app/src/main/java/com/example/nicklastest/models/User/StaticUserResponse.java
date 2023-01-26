package com.example.nicklastest.models.User;

import java.time.LocalDateTime;

public class StaticUserResponse {
    public int UserID;
    public String Email; 
    public String Password; 
    public String FirstName; 
    public String LastName; 
    public double Height;
    public int GenderID;
    public LocalDateTime BirthdayDate;
    public int UserPlanID;
    public LocalDateTime Created_At;
    public LocalDateTime Modified_At;
    public LocalDateTime Last_Login;

    public StaticUserResponse(int userID, String email, String password, String firstName, String lastName, double height, int genderID, LocalDateTime birthdayDate, int userPlanID, LocalDateTime created_At, LocalDateTime modified_At, LocalDateTime last_Login) {
        UserID = userID;
        Email = email;
        Password = password;
        FirstName = firstName;
        LastName = lastName;
        Height = height;
        GenderID = genderID;
        BirthdayDate = birthdayDate;
        UserPlanID = userPlanID;
        Created_At = created_At;
        Modified_At = modified_At;
        Last_Login = last_Login;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
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

    public LocalDateTime getCreated_At() {
        return Created_At;
    }

    public void setCreated_At(LocalDateTime created_At) {
        Created_At = created_At;
    }

    public LocalDateTime getModified_At() {
        return Modified_At;
    }

    public void setModified_At(LocalDateTime modified_At) {
        Modified_At = modified_At;
    }

    public LocalDateTime getLast_Login() {
        return Last_Login;
    }

    public void setLast_Login(LocalDateTime last_Login) {
        Last_Login = last_Login;
    }
}
