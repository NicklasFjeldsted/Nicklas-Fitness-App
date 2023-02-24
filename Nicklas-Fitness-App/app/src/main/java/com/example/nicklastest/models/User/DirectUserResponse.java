package com.example.nicklastest.models.User;
import com.example.nicklastest.models.Gender.StaticGenderResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DirectUserResponse {

    @SerializedName("userID")
    @Expose
    private Integer userID;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("height")
    @Expose
    private Integer height;
    @SerializedName("gender")
    @Expose
    private StaticGenderResponse gender;
    @SerializedName("birthdayDate")
    @Expose
    private String birthdayDate;
    @SerializedName("created_At")
    @Expose
    private String createdAt;
    @SerializedName("modified_At")
    @Expose
    private String modifiedAt;
    @SerializedName("last_Login")
    @Expose
    private String lastLogin;

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public StaticGenderResponse getGender() {
        return gender;
    }

    public void setGender(StaticGenderResponse gender) {
        this.gender = gender;
    }

    public String getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate(String birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(String modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

}
