package com.poc.belong.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.poc.belong.R;
import com.poc.belong.model.Facet;
import com.poc.belong.model.Folder;
import com.poc.belong.widget.CheckableRelativeLayout;

import java.util.ArrayList;

/**
 * Created by Praveen on 10/26/2015.
 */
public class FilterListAdapter extends  RecyclerView.Adapter<FilterListAdapter.ViewHolder> {

    private Folder mFolder;

    public interface IFacetChangeListener {
        void onFacetChecked(Folder folder, Facet checked);
    }

    private ArrayList<Facet> mFacetList;
    private IFacetChangeListener mFacetCheckListener;

    public FilterListAdapter(IFacetChangeListener facetCheckListener){
        mFacetCheckListener = facetCheckListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.filter_row_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,final int position) {
        final Facet facet = mFacetList.get(position);
        holder.productLabel.setText(facet.getLabel() + " ("+facet.getCount() +")");
        holder.layoutFilterRow.setChecked(facet.isSelected());
        holder.layoutFilterRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((CheckableRelativeLayout) view).toggle();
                boolean isChecked = ((CheckableRelativeLayout) view).isChecked();
                mFacetList.get(position).setIsSelected(isChecked);
                mFacetCheckListener.onFacetChecked(mFolder, facet);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFacetList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView productLabel;
        private final CheckableRelativeLayout layoutFilterRow;

        public ViewHolder(View itemView) {
            super(itemView);
            productLabel = (TextView) itemView.findViewById(R.id.txt_product_label);
            layoutFilterRow = (CheckableRelativeLayout) itemView.findViewById(R.id.filter_row);
        }
    }

    public void setFilters(Folder folder) {
        mFolder = folder;
        mFacetList = mFolder.getFacetList();
        notifyDataSetChanged();
    }
}
