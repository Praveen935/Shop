package com.poc.belong.utils;

import com.poc.belong.model.Facet;

import java.util.List;

/**
 * Created by Praveen on 10/25/2015.
 */
public class URLUtils {
    public static final String BASE_URL = "http://api.buyingiq.com/v1/search/?";
    public static final String LAUNCH_URL = "http://api.buyingiq.com/v1/search/?tags=mobiles&facet=1&page=1";


    public static String getNextPageUrl(String tags){
        return new StringBuilder(BASE_URL).append(tags).toString();
    }

    public static String getUrlFromTagList(List<String> tags){
        StringBuilder url = new StringBuilder(LAUNCH_URL);
        for(String tag : tags) {
            url.append("&tags=").append(tag);
        }
        return url.toString();
    }
}
