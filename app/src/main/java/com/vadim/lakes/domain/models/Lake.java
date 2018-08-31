package com.vadim.lakes.domain.models;

import com.google.gson.annotations.SerializedName;

public class Lake {

    @SerializedName("id")
    private Integer mId;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("img")
    private String mImg;
    @SerializedName("lat")
    private Double mLat;
    @SerializedName("lon")
    private Double mLon;

    public Lake(Integer id, String title, String description, String img, Double lat, Double lon) {
        this.mId = id;
        this.mTitle = title;
        this.mDescription = description;
        this.mImg = img;
        this.mLat = lat;
        this.mLon = lon;
    }

    public Lake() {
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        this.mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }

    public String getImg() {
        return mImg;
    }

    public void setImg(String img) {
        this.mImg = img;
    }

    public Double getLat() {
        return mLat;
    }

    public void setLat(Double lat) {
        this.mLat = lat;
    }

    public Double getLon() {
        return mLon;
    }

    public void setLon(Double lon) {
        this.mLon = lon;
    }
}
