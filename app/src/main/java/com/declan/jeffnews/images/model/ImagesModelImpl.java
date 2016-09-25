package com.declan.jeffnews.images.model;

import android.util.Log;

import com.declan.jeffnews.beans.ImagesBean;
import com.declan.jeffnews.utils.ImagesJsonUtils;
import com.declan.jeffnews.utils.OkHttpUtils;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.List;

/**
 * Created by Jeff_xu on 2016/9/23.
 */
public class ImagesModelImpl implements ImagesModel {

    private static final String TAG = "ImagesModelImpl";

    /**
     * 获取图片列表
     * @param
     */
    @Override
    public void loadImagesList(final OnLoadImageListListener listener) {
        Log.e(TAG,"加载图片");

        String url = "http://api.laifudao.com/open/tupian.json";
        OkHttpUtils.ResultCallback<String> loadImagesCallback = new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(String response) {
                List<ImagesBean> imagesBeans = ImagesJsonUtils.readJsonImagesBean(response);
                listener.onSuccess(imagesBeans);
                Log.e(TAG,response);
            }

            @Override
            public void onFailure(Exception e) {
                Log.e(TAG,"加载失败");
                listener.onFailure("加载失败",e);
            }
        };
        OkHttpUtils.get(url,loadImagesCallback);

    }

    public interface OnLoadImageListListener {
        void onSuccess(List<ImagesBean> list);
        void onFailure(String msg, Exception e);
    }
}
