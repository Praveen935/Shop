package com.poc.belong.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Praveen on 10/24/2015.
 */
public class Folder implements Serializable {

    @SerializedName("uri")
    private String uri;

    @SerializedName("name")
    private String folderName;

    @SerializedName("facets")
    private ArrayList<Facet> facetList;

    public String getUri() {
        return uri;
    }

    public String getFolderName() {
        return folderName;
    }

    public ArrayList<Facet> getFacetList() {
        return facetList;
    }

    @Override
    public boolean equals(Object o) {
        Folder folder = (Folder)o;
        try {
            return this.uri == folder.uri && this.folderName == folder.folderName;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
