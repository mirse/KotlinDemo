package com.wdz.common.base.adapter;

import android.content.Context;

import java.util.List;

public abstract class SingleSelectTypeAdapter<T> extends BaseRecyclerViewAdapter{


    /**
     * 默认-1为无选中项
     */
    private int mSelectedIndex = -1;


    public SingleSelectTypeAdapter(Context mContext, List<T> list) {
        super(mContext, list);
        mSelectedIndex = -1;
    }


    @Override
    public void onBindViewHolder(BaseViewHolder holder, int type, Object data, int position) {
        if (type == VIEW_TYPE_NORMAL){
            bindData(holder,(T) data,position);
        }
    }

    /**
     * 设置选中的item
     *
     * @param position
     */
    public void setItemChecked(int position) {
        mSelectedIndex = position;
    }

    /**
     * 获取选中的position
     * @return
     */
    public int getSelectPosition(){
        return mSelectedIndex;
    }

    /**
     * 绑定数据源
     * @param holder
     * @param data
     * @param position
     */
    public abstract void bindData(BaseViewHolder holder,T data, int position);
}
