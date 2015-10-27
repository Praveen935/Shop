package com.poc.belong.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.poc.belong.R;
import com.poc.belong.adapter.FilterListAdapter;
import com.poc.belong.adapter.FoldersPagerAdapter;
import com.poc.belong.adapter.ProductListAdapter;
import com.poc.belong.model.Facet;
import com.poc.belong.model.Folder;
import com.poc.belong.model.IDataModel;
import com.poc.belong.model.ProductData;
import com.poc.belong.presenter.IProductListingView;
import com.poc.belong.presenter.ProductListingPresenter;
import com.poc.belong.utils.AppUtils;
import com.poc.belong.utils.URLUtils;
import com.poc.belong.widget.SimpleDividerItemDecoration;
import com.poc.belong.widget.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;


public class ProductListingActivity extends AppCompatActivity implements IProductListingView, FilterListAdapter.IFacetChangeListener, SlidingTabLayout.ITabClickListener {

    private ProductListingPresenter productListingPresenter;
    private SlidingTabLayout mTabLayout;
    private ViewPager mViewPager;
    private ProgressBar mProgressBar;
    private ProductListAdapter mProductListAdapter;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearlayoutManager;
    private boolean isLoading;
    private boolean nextPageLoading;
    private ProductData mProductData;
    private FoldersPagerAdapter mFolderPagerAdapter;
    private List<Folder> mFolders;
    private List<String> mListSelectedTags = new ArrayList<>();
    private RelativeLayout mViewPagerFolder;
    private Button mBtnClearFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        setTitle("Mobile");
        initView();
        productListingPresenter = new ProductListingPresenter(this);
        productListingPresenter.getProductListingData(getApplicationContext(), URLUtils.LAUNCH_URL);
    }

    public void initView(){
        mProgressBar = (ProgressBar)findViewById(R.id.progress_bar);
        mTabLayout = (SlidingTabLayout) findViewById(R.id.tab_folders);
        mTabLayout.setCustomTabView(R.layout.tab_layout, R.id.title);
        mTabLayout.setTabClickListener(this);
        mViewPager = (ViewPager) findViewById(R.id.viewpager_facet);
        mViewPager.setOffscreenPageLimit(1);

        mFolderPagerAdapter = new FoldersPagerAdapter(getSupportFragmentManager(), null);

        mRecyclerView = (RecyclerView)findViewById(R.id.list_view_products);
        mLinearlayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLinearlayoutManager);
        mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(getApplicationContext()));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mProductListAdapter = new ProductListAdapter(getApplicationContext());
        mRecyclerView.setAdapter(mProductListAdapter);
        registerPaginationListener();
        mViewPagerFolder = (RelativeLayout) findViewById(R.id.lyt_folder_container);
        int height = AppUtils.getScreenHeight(this);
        RelativeLayout relativeLayout = (RelativeLayout)findViewById(R.id.lyt_facet_container);
        relativeLayout.getLayoutParams().height = (int) (height * 0.6);
        mBtnClearFilter = (Button) findViewById(R.id.btn_clear_filter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDataLoaded(IDataModel dataObject) {
        mProductData = (ProductData)dataObject;
        if(nextPageLoading){
            nextPageLoading = false;
            mProductListAdapter.updateList(mProductData.getProductList());
        } else {
            loadFolder();
            mProductListAdapter.setProducts(mProductData.getProductList());
        }
    }

    private void loadFolder(){
        mFolders = mProductData.getFolderList();
        if(mFolders != null && mFolders.size() > 1){
            mFolders.remove(0);// Remove categories
        }
        mFolderPagerAdapter = new FoldersPagerAdapter(getSupportFragmentManager(), mFolders);
        mViewPager.setAdapter(mFolderPagerAdapter);
        mTabLayout.setViewPager(mViewPager);
    }

    @Override
    public void onDataLoadError(String errorMessage) {

    }

    @Override
    public void showProgress() {
        isLoading = true;
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        isLoading = false;
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showNoNetwork() {
        mProgressBar.setVisibility(View.GONE);
        Toast.makeText(this, "Please check your network connection and try again.", Toast.LENGTH_LONG).show();
    }

    private void registerPaginationListener() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int visibleItemCount = mLinearlayoutManager.getChildCount();
                int totalItemCount = mLinearlayoutManager.getItemCount();
                int pastVisibleItems = mLinearlayoutManager.findFirstVisibleItemPosition();
                if (!isLoading) {
                    if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                        if (!TextUtils.isEmpty(mProductData.getNext())) {
                            String url = URLUtils.getNextPageUrl(mProductData.getNext());
                            nextPageLoading = true;
                            productListingPresenter.getProductListingData(getApplicationContext(), url);

                        }
                    }
                }
            }
        });
    }

    @Override
    public void onFacetChecked(Folder folder, Facet checkedFacet) {
        String facetTag = checkedFacet.getTag();
        if(checkedFacet.isSelected()) {
            if(!mListSelectedTags.contains(facetTag)) {
                mListSelectedTags.add(facetTag);
            }
        } else {
            if(mListSelectedTags.contains(facetTag)) {
                mListSelectedTags.remove(facetTag);
            }
        }

        if(mFolders.contains(folder)) {
            Folder actualFolder = mFolders.get(mFolders.indexOf(folder));
            ArrayList<Facet> facets = actualFolder.getFacetList();
            if(facets.contains(checkedFacet)){
                facets.get(facets.indexOf(checkedFacet)).setIsSelected(checkedFacet.isSelected());
            }
        }
        setClearButtonText();
    }

    public void onFacetApply(View v) {
        mViewPagerFolder.setVisibility(View.GONE);
        mViewPager.setVisibility(View.GONE);
        if(mListSelectedTags != null && mListSelectedTags.size() > 0) {
            String url = URLUtils.getUrlFromTagList(mListSelectedTags);
            productListingPresenter.getProductListingData(getApplicationContext(), url);
        }else {
            productListingPresenter.getProductListingData(getApplicationContext(), URLUtils.LAUNCH_URL);
        }
    }

    public void onFacetClear(View v) {
        mViewPager.setVisibility(View.GONE);
        mViewPagerFolder.setVisibility(View.GONE);
        for(Folder folder : mFolders){
            for(Facet facet : folder.getFacetList()){
                facet.setIsSelected(false);
            }
        }
        if(mListSelectedTags != null && mListSelectedTags.size() > 0){
            mFolderPagerAdapter.clearFacetData();
            mFolderPagerAdapter.updateFolders(mFolders);
            mListSelectedTags.clear();
            setClearButtonText();
            productListingPresenter.getProductListingData(getApplicationContext(), URLUtils.LAUNCH_URL);
        }
    }

    public void onBackPressed() {
        if(mViewPagerFolder.getVisibility() == View.VISIBLE) {
            mViewPagerFolder.setVisibility(View.GONE);
            mViewPager.setVisibility(View.GONE);
            return;
        }
        if(productListingPresenter != null){
            productListingPresenter.unregisterViewListener();
        }
        super.onBackPressed();
    }

    @Override
    public void setFolderContainerVisibility(int visibility) {
        mViewPagerFolder.setVisibility(visibility);
        mViewPager.setVisibility(visibility);
    }

    private void setClearButtonText(){
        String text = "Clear All";
        if(mListSelectedTags !=  null && mListSelectedTags.size() > 0) {
            text = text + " (" +mListSelectedTags.size() + ")";
        }
        mBtnClearFilter.setText(text);
    }

    public void onFolderContainerClick(View v){
        setFolderContainerVisibility(View.GONE);
    }
}
