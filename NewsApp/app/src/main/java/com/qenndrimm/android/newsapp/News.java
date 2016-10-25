package com.qenndrimm.android.newsapp;

/**
 * Created by qenndrimm on 7/3/2016.
 */

public class News {

    private String title;
    private final String NO_IMAGE = "No image";
    private String imageAddress = NO_IMAGE;
    private boolean hasImage;
    private String webAddress;

    News(String title, String imageAddress, String webAddress) {
        this.title = title;
        this.imageAddress = imageAddress;
        this.webAddress = webAddress;
    }

    News() {
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setImageAddress(String imageAddress) {
        this.imageAddress = imageAddress;
    }

    public String getImageAddress() {
        return imageAddress;
    }

    public boolean hasImage() {
        return (!imageAddress.equals(NO_IMAGE));
    }

    public void setWebAddress(String webAddress) {
        this.webAddress = webAddress;
    }

    public String getWebAddress() {
        return webAddress;
    }
}