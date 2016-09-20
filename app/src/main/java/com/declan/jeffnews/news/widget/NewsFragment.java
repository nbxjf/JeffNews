package com.declan.jeffnews.news.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.declan.jeffnews.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/15.
 */
public class NewsFragment extends Fragment {

    private static final int NEWS_TYPE_HOT = 1;
    private static final int NEWS_TYPE_NBA = 2;
    private static final int NEWS_TYPE_JOKE = 3;
    private static final int NEWS_TYPE_CIRCLE = 4;

    private ViewPager mViewPager;
    private TabLayout mTabLayout;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news,null);
        initControls(view);
//        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
//        tabLayout.addTab(tabLayout.newTab().setText("1"));
        return view;
    }

    /**
     * 初始化控件
     *
     * @param view
     */
    private void initControls(View view) {
        mTabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        mViewPager.setOffscreenPageLimit(3);

        MyFragmentPagerAdapter myFragmentPagerAdapter = new MyFragmentPagerAdapter(getChildFragmentManager());
        myFragmentPagerAdapter.addFragment(NewsListFragment.newInstance(NEWS_TYPE_HOT),getString(R.string.hot));
        myFragmentPagerAdapter.addFragment(NewsListFragment.newInstance(NEWS_TYPE_NBA),getString(R.string.nba));
        myFragmentPagerAdapter.addFragment(NewsListFragment.newInstance(NEWS_TYPE_JOKE),getString(R.string.joke));
        myFragmentPagerAdapter.addFragment(NewsListFragment.newInstance(NEWS_TYPE_CIRCLE), getString(R.string.circle));
        mViewPager.setAdapter(myFragmentPagerAdapter);

        //mTabLayout.setTabMode(TabLayout.MODE_FIXED);
//        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.hot));
//        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.nba));
//        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.joke));
//        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.circle));
        mTabLayout.setupWithViewPager(mViewPager);
    }

    public static class MyFragmentPagerAdapter extends FragmentPagerAdapter{

        public final List<Fragment> mFragments = new ArrayList<>();
        public final List<String> mFragmentTitles = new ArrayList<>();

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment,String title){
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}
