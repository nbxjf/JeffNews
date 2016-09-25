package com.declan.jeffnews.images.presenter;

import android.media.Image;
import android.nfc.Tag;
import android.util.Log;
import android.widget.ImageView;

import com.declan.jeffnews.beans.ImagesBean;
import com.declan.jeffnews.images.model.ImagesModel;
import com.declan.jeffnews.images.model.ImagesModelImpl;
import com.declan.jeffnews.images.view.ImagesView;

import java.util.List;

/**
 * Created by Jeff_xu on 2016/9/20.
 */
public class ImagesPresenterImpl implements ImagesPresenter,ImagesModelImpl.OnLoadImageListListener{

    private static final String TAG = "ImagesPresenterImpl";

    private ImagesView mImagesView;
    private ImagesModel mImagesModel;

    public ImagesPresenterImpl(ImagesView imagesView) {
        this.mImagesView = imagesView;
        this.mImagesModel = new ImagesModelImpl();
    }

    @Override
    public void loadImages() {
        mImagesView.showProgress();
        Log.e("presenter", "presenter load images");
        mImagesModel.loadImagesList(this);
    }


    @Override
    public void onSuccess(List<ImagesBean> list) {
        Log.e(TAG,list.size()+"");
        mImagesView.addImages(list);
        mImagesView.hideProgress();
    }

    @Override
    public void onFailure(String msg, Exception e) {
        mImagesView.hideProgress();
    }
}
