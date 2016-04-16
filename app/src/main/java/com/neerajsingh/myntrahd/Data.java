package com.neerajsingh.myntrahd;

public class Data {

    private String description;

    private String imagePath;

    private String videoCode;

    public String getDescription() {
        return description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getVideoCode() {
        return videoCode;

    }

    public Data(String imagePath,String description,  String videoCode) {
        this.description = description;
        this.imagePath = imagePath;
        this.videoCode = videoCode;
    }
}
