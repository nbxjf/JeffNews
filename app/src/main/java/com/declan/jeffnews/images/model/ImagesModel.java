package com.declan.jeffnews.images.model;

/**
 * Created by Jeff_xu on 2016/9/20.
 */
public interface ImagesModel {
    void loadImagesList(ImagesModelImpl.OnLoadImageListListener listener);
}
