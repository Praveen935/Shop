package com.poc.belong.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Praveen on 10/24/2015.
 */
public class Facet implements Serializable{

    @SerializedName("label")
    private String label;

    @SerializedName("tag")
    private String tag;

    @SerializedName("count")
    private String count;

    @SerializedName("is_selected")
    private boolean isSelected;

    public String getLabel() {
        return label;
    }

    public String getTag() {
        return tag;
    }

    public String getCount() {
        return count;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    @Override
    public boolean equals(Object o) {
        Facet facet = (Facet)o;
        return this.tag == facet.tag;
    }
}
