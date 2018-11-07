package com.seeme.daniel.seepic.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.seeme.daniel.seepic.PhotoDetailsActivity;
import com.seeme.daniel.seepic.R;
import com.seeme.daniel.seepic.entity.PhotoBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author danielwang
 * @Description:
 * @date 2018/11/7 18:17
 */
public class StaggeredRecycleViewAdapter extends RecyclerView.Adapter<StaggeredRecycleViewAdapter.ViewHolder> implements View.OnClickListener {
    private Context mContext;
    private List<PhotoBean.DataBean> photolist = new ArrayList<PhotoBean.DataBean>();
    private OnItemClickListener mOnItemClickListener = null;
    public StaggeredRecycleViewAdapter(Context context) {
        mContext = context;
    }

    //define interface
    public  interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(v,(int)v.getTag());
        }
    }
    public void clearNewData() {
        this.photolist.clear();
    }

    public void addAllNewsData(List<PhotoBean.DataBean> photolist) {
        this.photolist.addAll(photolist);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv_photo;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_photo = (ImageView) itemView.findViewById(R.id.iv_photo);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.staggered_recycler_view_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        //将position保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setTag(position);
        Glide.with(mContext)
                .load(photolist.get(position).getImage_url())
                .placeholder(R.mipmap.girls)
                .into(holder.iv_photo);

        holder.iv_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PhotoDetailsActivity.class);
                intent.putExtra("img_url", photolist.get(position).getImage_url());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return photolist.size();
    }
}
