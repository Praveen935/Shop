
package com.poc.belong.net;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


public class VolleyHelper {

	private static RequestQueue mRequestQueue;
    private static VolleyHelper mInstance;
	private VolleyHelper(Context context) {
        getRequestQueue(context);
    }

    public static VolleyHelper getInstance(Context context){
        if(mInstance == null){
            synchronized (VolleyHelper.class){
                if ((mInstance == null)) {
                    mInstance = new VolleyHelper(context);
                }
            }
        }
        return mInstance;
    }
	
	public RequestQueue getRequestQueue(Context context) {
		if(mRequestQueue == null){
            mRequestQueue = Volley.newRequestQueue(context);
		}
		return mRequestQueue;
	}

	public void addRequest(Context context, Request request) {
        getRequestQueue(context).add(request);
	}
}
