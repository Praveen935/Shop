package com.poc.belong.net;

import com.poc.belong.model.IDataModel;

/**
 * Created by Praveen on 10/24/2015.
 */
public interface IDataLoadedListener {
    void onDataLoadSuccess(IDataModel dataModel);
    void onDataLoadError(String errorMessage);
}
