package com.declan.jeffnews.images.view;

import com.declan.jeffnews.beans.ImagesBean;

import java.util.List;

/**
 * Created by Jeff_xu on 2016/9/23.
 */
public interface ImagesView {
    void addImages(List<ImagesBean> list);
    void showProgress();
    void hideProgress();
    void showLoadFailMsg();
}
