package com.qenndrimm.android.booklistingapp;

/**
 * Created by qenndrimm on 7/3/2016.
 */
public class Book {
    private String title;
    private String author;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
}
