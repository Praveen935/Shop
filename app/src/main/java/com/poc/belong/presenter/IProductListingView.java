package com.poc.belong.presenter;

import com.android.volley.VolleyError;
import com.poc.belong.model.IDataModel;

/**
 * Created by Praveen on 10/24/2015.
 */
public interface IProductListingView {
    void onDataLoaded(IDataModel dataObject);
    void onDataLoadError(String errorMessage);
    void showProgress();
    void hideProgress();
    void showNoNetwork();
}
