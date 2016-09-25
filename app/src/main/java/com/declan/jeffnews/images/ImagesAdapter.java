package com.declan.jeffnews.images;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.declan.jeffnews.R;
import com.declan.jeffnews.beans.ImagesBean;
import com.declan.jeffnews.images.view.ImagesView;
import com.declan.jeffnews.utils.ImagesLoderUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeff_xu on 2016/9/23.
 */
public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ItemViewHolder> {

    private static final String TAG = "ImagesAdapter";
    private Context mContext;
    private int mV_width;
    private int mV_height;


    public ImagesAdapter(Context context) {
        this.mContext = context;
        float scale = context.getResources().getDisplayMetrics().density;
        mV_width = context.getResources().getDisplayMetrics().widthPixels - (int)(scale * 20 + 0.5f);
        mV_width = mV_width / 2;

        mV_height = context.getResources().getDisplayMetrics().heightPixels - (int)(scale * 30 + 0.5f);
    }

    private List<ImagesBean> mData = new ArrayList<ImagesBean>();

    public void setmDate(List<ImagesBean> data) {
        Log.e(TAG,data.size()+"");
        this.mData = data;
        this.notifyDataSetChanged();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.images_item,parent,false);
        ItemViewHolder vh = new ItemViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        ImagesBean bean = mData.get(position);
        holder.mTextView.setText(bean.getTitle());
        int width = bean.getWidth();
        float scale = (float) mV_width / (float) width;
        int height = (int)(bean.getHeight()/scale);
        if(height > mV_height){
            height = mV_height;
        }
        holder.mImageView.setLayoutParams(new LinearLayout.LayoutParams(mV_width,height));
        ImagesLoderUtils.display(mContext,holder.mImageView,bean.getThumburl());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder{

        private TextView mTextView;
        private ImageView mImageView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv_images);
            mImageView = (ImageView) itemView.findViewById(R.id.iv_images);
        }
    }
}
