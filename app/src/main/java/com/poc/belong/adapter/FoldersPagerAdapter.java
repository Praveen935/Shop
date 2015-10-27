package com.poc.belong.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.poc.belong.fragment.FacetFragment;
import com.poc.belong.model.Folder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Praveen on 10/26/2015.
 */
public class FoldersPagerAdapter extends FragmentStatePagerAdapter {

    private List<Folder> mFolderList = new ArrayList<>();
    private SparseArray<Fragment> registeredFragments = new SparseArray<Fragment>();


    public FoldersPagerAdapter(FragmentManager fm, List<Folder> folderList) {
        super(fm);
        mFolderList = folderList;
    }

    @Override
    public Fragment getItem(int position) {
        Folder folder = mFolderList.get(position);
        Fragment fragment = FacetFragment.newInstance(folder);
        registeredFragments.put(position, fragment);
        return fragment;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        registeredFragments.remove(position);
        super.destroyItem(container, position, object);
    }

    @Override
    public int getCount() {
        return mFolderList == null ? 0 : mFolderList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Folder folder = mFolderList.get(position);
        return folder.getFolderName();
    }

    public void updateFolders(List<Folder> folderList) {
        mFolderList = folderList;
        notifyDataSetChanged();
    }

    public void clearFacetData(){
        if(registeredFragments.size() < 1) return;
        for(int i = 0; i < registeredFragments.size(); i++){
            FacetFragment fragment = (FacetFragment)registeredFragments.get(i);
            if(fragment != null){
                fragment.clearAllFacet();
            }
        }
    }


}
