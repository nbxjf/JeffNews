package com.declan.jeffnews.main.widget;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.declan.jeffnews.R;
import com.declan.jeffnews.about.widget.AboutFragment;
import com.declan.jeffnews.images.widget.ImagesFragment;
import com.declan.jeffnews.main.presenter.MainPresenter;
import com.declan.jeffnews.main.presenter.MainPresenterImpl;
import com.declan.jeffnews.main.view.MainView;
import com.declan.jeffnews.news.widget.NewsFragment;
import com.declan.jeffnews.weather.widget.WeatherFragment;

/**
 * Author: Jeff_xu
 */
public class MainActivity extends AppCompatActivity implements MainView{

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView mNavigationView;
    private MainPresenter mMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("新闻");
        setSupportActionBar(mToolbar);

        //设置toggle图案
        mDrawerLayout = (DrawerLayout)findViewById(R.id.id_drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open,
                R.string.drawer_close);
        mDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mNavigationView = (NavigationView) findViewById(R.id.id_nv_menu);
        setupDrawerContent(mNavigationView);

        mMainPresenter = new MainPresenterImpl(this);

        switch2news();
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        mMainPresenter.switchNavigation(menuItem.getItemId());
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

//    跳转到newsfragment
    @Override
    public void switch2news() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content,new NewsFragment()).commit();
        mToolbar.setTitle(R.string.navigation_news);
    }

    @Override
    public void switch2image() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content,new ImagesFragment()).commit();
        mToolbar.setTitle(R.string.navigation_images);
    }

    @Override
    public void switch2weather() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content,new WeatherFragment()).commit();
        mToolbar.setTitle(R.string.navigation_weather);
    }

    @Override
    public void switch2about() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content,new AboutFragment()).commit();
        mToolbar.setTitle(R.string.navigation_about);
    }
}
