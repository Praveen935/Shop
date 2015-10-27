package com.poc.belong.net;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.poc.belong.model.IDataModel;
import com.poc.belong.model.ProductData;

/**
 * Created by Praveen on 10/24/2015.
 */
public class DataLoader {
    private final IDataLoadedListener dataLoadedListener;
    public DataLoader(IDataLoadedListener dataLoadedListener){
        this.dataLoadedListener = dataLoadedListener;
    }

    public void getProductDataFromServer(Context context, String url){
        GSonRequest request = new GSonRequest(url, responseListener, errorListener, new ProductData());
        VolleyHelper.getInstance(context).addRequest(context, request);
    }

    private final Response.Listener<IDataModel> responseListener = new Response.Listener<IDataModel>() {
        @Override
        public void onResponse(IDataModel response) {
            dataLoadedListener.onDataLoadSuccess(response);
        }
    };

    private final Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            dataLoadedListener.onDataLoadError(error.getMessage());
        }
    };
}
