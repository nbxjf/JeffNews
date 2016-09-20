package com.declan.jeffnews.main.presenter;

import com.declan.jeffnews.R;
import com.declan.jeffnews.main.view.MainView;

/**
 * Created by Administrator on 2016/9/15.
 */
public class MainPresenterImpl implements MainPresenter {

    private MainView mMainView;

    public MainPresenterImpl(MainView mainView) {
        this.mMainView = mainView;
    }

    @Override
    public void switchNavigation(int id) {
        switch (id){
            case R.id.navigation_news:
                mMainView.switch2news();
                break;
            case R.id.navigation_images:
                mMainView.switch2image();
                break;
            case R.id.navigation_weather:
                mMainView.switch2weather();
                break;
            case R.id.navigation_about:
                mMainView.switch2about();
                break;
        }
    }
}
