package com.poc.belong.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Praveen on 10/25/2015.
 */
public class Product {

    @SerializedName("name")
    private String name;

    @SerializedName("avg_rating")
    private String avgRating;

    @SerializedName("rating_count")
    private String ratingCount;

    @SerializedName("store_count")
    private String storeCount;

    @SerializedName("min_price")
    private String minPrice;

    @SerializedName("images")
    private List<ProductThumbnail> productThumbnails;

    public String getName() {
        return name;
    }

    public String getAvgRating() {
        return avgRating;
    }

    public String getRatingCount() {
        return ratingCount;
    }

    public String getStoreCount() {
        return storeCount;
    }

    public String getMinPrice() {
        return minPrice;
    }

    public List<ProductThumbnail> getProductThumbnails() {
        return productThumbnails;
    }
}
