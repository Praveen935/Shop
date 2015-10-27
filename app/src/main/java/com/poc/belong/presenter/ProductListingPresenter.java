package com.poc.belong.presenter;

import android.content.Context;

import com.poc.belong.model.IDataModel;
import com.poc.belong.net.DataLoader;
import com.poc.belong.net.IDataLoadedListener;
import com.poc.belong.utils.AppUtils;

/**
 * Created by Praveen on 10/24/2015.
 */
public class ProductListingPresenter implements IDataLoadedListener {

    IProductListingView mProductListingView;
    private final DataLoader dataLoader;

    public ProductListingPresenter(IProductListingView productListingView){
        mProductListingView = productListingView;
        dataLoader = new DataLoader(this);
    }

    public void getProductListingData(Context context, String url){
        if(!AppUtils.isNetworkAvailable(context)){
            if(mProductListingView != null){
                mProductListingView.showNoNetwork();
            }
            return;
        }
        dataLoader.getProductDataFromServer(context, url);
        if(mProductListingView != null){
            mProductListingView.showProgress();
        }
    }

    @Override
    public void onDataLoadError(String errorMessage) {
        if(mProductListingView != null){
            mProductListingView.hideProgress();
            mProductListingView.onDataLoadError(errorMessage);
        }
    }

    @Override
    public void onDataLoadSuccess(IDataModel dataModel) {
        if(mProductListingView != null){
            mProductListingView.hideProgress();
            mProductListingView.onDataLoaded(dataModel);
        }
    }

    public void unregisterViewListener() {
        mProductListingView = null;
    }
}
