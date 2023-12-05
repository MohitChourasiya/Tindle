package com.user.tindle.Models;

public class cards {
    private String userId;

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public  String profileImageUrl;

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    private String lastname;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    public cards (String userId,String name,String lastname,String profileImageUrl){

        this.lastname = lastname;
        this.userId = userId;
        this.name = name;
        this.profileImageUrl = profileImageUrl;
    }

}
