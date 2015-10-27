package com.poc.belong.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Praveen on 10/25/2015.
 */
public class ProductThumbnail {

    @SerializedName("75x75")
    private String thumbnailUrl75;

    @SerializedName("125x125")
    private String thumbnailUrl125;

    @SerializedName("180x180")
    private String thumbnailUrl80;

    @SerializedName("360x360")
    private String thumbnailUrl360;


    public String getThumbnailUrl75() {
        return thumbnailUrl75;
    }

    public String getThumbnailUrl125() {
        return thumbnailUrl125;
    }

    public String getThumbnailUrl80() {
        return thumbnailUrl80;
    }

    public String getThumbnailUrl360() {
        return thumbnailUrl360;
    }
}
