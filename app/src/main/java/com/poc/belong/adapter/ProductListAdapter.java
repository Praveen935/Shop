package com.poc.belong.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.poc.belong.R;
import com.poc.belong.model.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Praveen on 10/25/2015.
 */
public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder>{

    private List<Product> mProductList;
    private Context mContext;

    public ProductListAdapter(Context context){
        mProductList = new ArrayList<>();
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_row_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Product productData = mProductList.get(i);
        if (productData != null) {
            viewHolder.productPrice.setText("Rs. " + productData.getMinPrice());
            viewHolder.productName.setText(productData.getName());
            String url = productData.getProductThumbnails().get(0).getThumbnailUrl360();
            viewHolder.storeCount.setText(productData.getStoreCount() + " Stores");
            viewHolder.ratingValue.setText(productData.getAvgRating());
            viewHolder.ratingCount.setText(productData.getRatingCount() + " Votes");
            Picasso.with(mContext).load(url).into(viewHolder.productImage);
        }
    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView productName;
        private final ImageView productImage;
        private final TextView productPrice;
        private final TextView storeCount;
        private final TextView ratingValue;
        private final TextView ratingCount;

        public ViewHolder(View itemView) {
            super(itemView);
            productName = (TextView) itemView.findViewById(R.id.txt_product_name);
            ratingValue = (TextView) itemView.findViewById(R.id.txt_rating_bar);
            ratingCount = (TextView) itemView.findViewById(R.id.txt_rating_count);
            storeCount = (TextView) itemView.findViewById(R.id.txt_store_count);
            productPrice = (TextView) itemView.findViewById(R.id.txt_product_price);
            productImage = (ImageView) itemView.findViewById(R.id.image_view_product_thumbnail);
        }
    }

    public void updateList(List<Product> products){
        mProductList.addAll(products);
        notifyDataSetChanged();
    }

    public void setProducts(List<Product> productList) {
        mProductList = productList;
        notifyDataSetChanged();
    }
}
