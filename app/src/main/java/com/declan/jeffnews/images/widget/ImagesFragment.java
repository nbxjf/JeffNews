package com.declan.jeffnews.images.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.declan.jeffnews.R;
import com.declan.jeffnews.beans.ImagesBean;
import com.declan.jeffnews.images.ImagesAdapter;
import com.declan.jeffnews.images.presenter.ImagesPresenter;
import com.declan.jeffnews.images.presenter.ImagesPresenterImpl;
import com.declan.jeffnews.images.view.ImagesView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeff_xu on 2016/9/15.
 */
public class ImagesFragment extends Fragment implements ImagesView, SwipeRefreshLayout.OnRefreshListener{

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    //private LinearLayoutManager mLayoutManager;

    private List<ImagesBean> mData ;
    private ImagesAdapter mImagesAdapter;
    private ImagesPresenter mImagesPresenter;
    private StaggeredGridLayoutManager mStaggeredGridLayoutManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImagesPresenter = new ImagesPresenterImpl(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_images,null);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_images);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.primary,
                R.color.primary_dark, R.color.primary_light,
                R.color.accent);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_images);
        mRecyclerView.setHasFixedSize(true);
        /*mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager); //设置recyclerview的线性竖直管理器*/

        mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mStaggeredGridLayoutManager);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mImagesAdapter = new ImagesAdapter(getActivity().getApplicationContext());
        mRecyclerView.setAdapter(mImagesAdapter);
        mRecyclerView.addOnScrollListener(mOnScrollListener);
        onRefresh();
        return view;
    }

    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {
        private int lastVisibleItem;
        private int[] lastpositions;
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE
                    && lastVisibleItem + 1 == mImagesAdapter.getItemCount() ) {
                //加载更多
                Snackbar.make(getActivity().findViewById(R.id.id_drawer_layout), getString(R.string.image_hit), Snackbar.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            lastpositions = new int[mStaggeredGridLayoutManager.getSpanCount()];
            mStaggeredGridLayoutManager.findLastVisibleItemPositions(lastpositions);
            int max = lastpositions[0];
            for (int value : lastpositions){
                if(value  > max){
                    max = value;
                }
            }
            lastVisibleItem = max;
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    /**
     * 刷新监听
     */
    @Override
    public void onRefresh() {
        if(mData != null){
            mData.clear();
        }
        mImagesPresenter.loadImages();
    }

    @Override
    public void addImages(List<ImagesBean> list) {
        if(mData == null) {
            mData = new ArrayList<ImagesBean>();
        }
        mData.addAll(list);
        mImagesAdapter.setmDate(mData);
    }

    @Override
    public void showProgress() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showLoadFailMsg() {

    }
}
