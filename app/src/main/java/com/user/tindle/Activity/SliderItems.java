package com.user.tindle.Activity;

public class SliderItems {

    private int image;
    String description;
    String details;

    SliderItems(int image,String description,String details) {
        this.image = image;
        this.description = description;
        this.details = details;
    }
    public int getImage() {
        return image;
    }

    public String getDetails(){return details;}

    public String getDescription(){return description;}
}
