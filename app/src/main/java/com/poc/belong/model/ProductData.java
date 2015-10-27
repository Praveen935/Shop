package com.poc.belong.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Praveen on 10/24/2015.
 */
public class ProductData implements IDataModel {
    @SerializedName("took")
    private String timeTaken;

    public String getTimeTaken(){
        return timeTaken;
    }

    @SerializedName("products")
    private List<Product> productList;

    @SerializedName("next")
    private String next;

    @SerializedName("folders")
    private List<Folder> folderList;

    public List<Product> getProductList() {
        return productList;
    }

    public List<Folder> getFolderList() {
        return folderList;
    }

    public String getNext() {
        return next;
    }
}
