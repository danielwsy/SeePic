package com.seeme.daniel.seepic.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.seeme.daniel.seepic.R;


/**
 * @author danielwang
 * @Description:
 * @date 2018/11/13 11:12
 */
public class LocalItemView extends LinearLayout {
    private ImageView mIcon, mPlay;
    private TextView mName;
    private TextView mDesc;
    private Context mContext;
    private int position;

    public LocalItemView(Context context) {
        super(context, null);
    }

    public LocalItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public LocalItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.LocalItemView);
        String name = ta.getString(R.styleable.LocalItemView_tv_name);
        String desc = ta.getString(R.styleable.LocalItemView_tv_desc);
        Drawable drawable = ta.getDrawable(R.styleable.LocalItemView_iv_icon);
        ColorStateList mDrawableTintList = ta.getColorStateList(R.styleable.LocalItemView_iv_icon_color);
        ta.recycle();

        mContext = context;

        LayoutInflater inflater = LayoutInflater.from(context);
        View mView = inflater.inflate(R.layout.item_layout_view, this, true);
        mIcon = (ImageView) mView.findViewById(R.id.iv_icon);
        mPlay = (ImageView) mView.findViewById(R.id.iv_play);
        mName = (TextView) mView.findViewById(R.id.tv_name);
        mDesc = (TextView) mView.findViewById(R.id.tv_desc);

        mName.setText(name);
        mDesc.setText(desc);
        mIcon.setImageDrawable(drawable);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mIcon.setImageTintList(mDrawableTintList);
            mIcon.setImageTintMode(PorterDuff.Mode.SRC_ATOP);
        }


        mPlay.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                }
            }
        });

        mView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                }
            }
        });

    }

    public void setSongsNum(int num, int position) {
        this.position = position;
        mDesc.setText(getResources().getString(R.string.song_num, num));
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void click(View view, int position);
    }
}
