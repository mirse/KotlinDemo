package com.wdz.common.base.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public abstract class SingleTypeAdapter<T> extends BaseRecyclerViewAdapter {
    private static final String TAG = "SingleTypeAdapter";
    private List<T> mList = new ArrayList<>();
    public SingleTypeAdapter(Context mContext, List<T> list) {
        super(mContext, list);
        mList = list;
    }
    @Override
    public void onBindViewHolder(BaseViewHolder holder, int type, Object data, int position) {
        if (type == VIEW_TYPE_NORMAL){
            bindData(holder,(T) data,position);
        }
    }
    /**
     * 绑定数据源
     */
    public abstract void bindData(BaseViewHolder holder,T data, int position);

}
