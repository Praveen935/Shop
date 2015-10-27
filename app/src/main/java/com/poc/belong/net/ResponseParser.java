package com.poc.belong.net;

import com.google.gson.Gson;
import com.poc.belong.model.IDataModel;

/**
 * Created by Praveen on 10/24/2015.
 */
public class ResponseParser {
    public static IDataModel parse(byte[] stream, IDataModel object){
        String jsonString = new String(stream);
        Gson gson = new Gson();
        IDataModel orderList = gson.fromJson(jsonString, object.getClass());
        return orderList;
    }
}
