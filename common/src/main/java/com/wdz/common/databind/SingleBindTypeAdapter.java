package com.wdz.common.databind;

import android.content.Context;

import androidx.databinding.ViewDataBinding;

import java.util.ArrayList;
import java.util.List;

public abstract class SingleBindTypeAdapter<T> extends BaseBindRecyclerViewAdapter {
    private static final String TAG = "SingleTypeAdapter";
    private List<T> mList = new ArrayList<>();
    public SingleBindTypeAdapter(Context mContext, List<T> list) {
        super(mContext, list);
        mList = list;
    }
    @Override
    public void onBindViewHolder(ViewDataBinding binding,BaseViewHolder holder, int type, Object data, int position) {
        if (type == VIEW_TYPE_NORMAL){
            bindData(binding,holder,(T) data,position);
        }
    }


    /**
     * 绑定数据源
     */
    public abstract void bindData(ViewDataBinding binding,BaseViewHolder holder,T data, int position);

}
