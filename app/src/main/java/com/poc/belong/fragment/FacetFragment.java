package com.poc.belong.fragment;

import android.app.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.poc.belong.R;
import com.poc.belong.adapter.FilterListAdapter;
import com.poc.belong.model.Facet;
import com.poc.belong.model.Folder;
import com.poc.belong.utils.AppUtils;
import com.poc.belong.widget.SimpleDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Praveen on 10/26/2015.
 */
public class FacetFragment extends Fragment {

    private FilterListAdapter.IFacetChangeListener mFacetCheckedListener;
    private RecyclerView mRecyclerView;
    private FilterListAdapter mFilterListAdapter;
    public static final String EXTRA_BUNDLE_FOLDER = "folder";
    private List<String> mListSelectedTags = new ArrayList<>();

    public static Fragment newInstance(Folder folder){
        FacetFragment facetFragment = new FacetFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_BUNDLE_FOLDER, folder);
        facetFragment.setArguments(bundle);
        return facetFragment;
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mFacetCheckedListener = ((FilterListAdapter.IFacetChangeListener)activity);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.facet_layout, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.list_view_filter);
        return view;
    }

    private Folder mFolders;
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFolders = (Folder) getArguments().getSerializable(EXTRA_BUNDLE_FOLDER);
        mFilterListAdapter = new FilterListAdapter(mFacetCheckedListener);
        mRecyclerView.setAdapter(mFilterListAdapter);
        mFilterListAdapter.setFilters(mFolders);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    public void clearAllFacet(){
        if(mFolders !=null && mFolders.getFacetList() != null) {
            for(Facet facet : mFolders.getFacetList()){
                facet.setIsSelected(false);
            }
            mFilterListAdapter.notifyDataSetChanged();
        }
    }

}
