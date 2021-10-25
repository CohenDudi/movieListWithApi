package com.example.testv1;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class movie implements Serializable,Comparable<movie> {
    @SerializedName("id")
    private long id;
    @SerializedName("year")
    private long year;
    @SerializedName("category")
    private String category;
    @SerializedName("name")
    private String name;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public long getRate() {
        return rate;
    }

    public void setRate(long rate) {
        this.rate = rate;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    @SerializedName("description")
    private String description;

    @SerializedName("imageUrl")
    private String imageUrl;

    @SerializedName("rate")
    private long rate;

    @SerializedName("hour")
    private String hour;

    public movie(){}

    public movie(long id, long year, String category, String name) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getYear() {
        return year;
    }

    public void setYear(long year) {
        this.year = year;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public int compareTo(movie o) {
        if(this.year == o.getYear())return 0;
        if(this.year > (o.getYear()))return 1;
        else return -1;
    }
}
