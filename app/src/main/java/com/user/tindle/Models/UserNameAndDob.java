package com.user.tindle.Models;

public class UserNameAndDob {

    String FirstName;
    String LastName;
    String Gender;
    String DOB;
    String Profession;
    String Location;

    public String getProfession() {
        return Profession;
    }

    public void setProfession(String profession) {
        Profession = profession;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getAbout() {
        return About;
    }

    public void setAbout(String about) {
        About = about;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    String About;
    String profileImageUrl;

    public UserNameAndDob() {

    }

    public String getGender() {
        return Gender;
    }

    public String setGender(String gender) {
        Gender = gender;
        return gender;
    }



    public String getFirstName() {
        return FirstName;
    }

    public String setFirstName(String firstName) {
        FirstName = firstName ;
        return firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String setLastName(String lastName) {
        LastName = lastName;
        return lastName;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

}
