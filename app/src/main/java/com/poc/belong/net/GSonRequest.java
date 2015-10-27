package com.poc.belong.net;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.poc.belong.model.IDataModel;
import com.poc.belong.model.ProductData;

/**
 * Created by Praveen on 10/24/2015.
 */
public class GSonRequest extends Request<IDataModel> {

    private final Response.Listener<IDataModel> mListener;
    private IDataModel dataObject;

    public GSonRequest(String url, Response.Listener<IDataModel> listener, Response.ErrorListener errorListener, IDataModel dataObject){
        super(Method.GET, url, errorListener);
        mListener = listener;
        this.dataObject = dataObject;
    }

    @Override
    protected Response<IDataModel> parseNetworkResponse(NetworkResponse response) {
        try {
            String responsedata = new String(response.data);

            ProductData responseObject = (ProductData)ResponseParser.parse(response.data, dataObject);

            return Response.success((IDataModel)responseObject, getCacheEntry());
        }catch (Exception e){
            return Response.error(new VolleyError("Parsing error"));
        }
    }

    @Override
    protected void deliverResponse(IDataModel dataObject) {
        mListener.onResponse(dataObject);
    }
}
