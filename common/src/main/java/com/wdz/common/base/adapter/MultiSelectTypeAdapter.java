package com.wdz.common.base.adapter;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dezhi.wang
 */
public abstract class MultiSelectTypeAdapter<T> extends BaseRecyclerViewAdapter{
    /**
     * 选中项位置数组
     */
    //HashMap<Integer,Boolean> mSelectList = new HashMap<>();
    private List<Integer> mSelectList = new ArrayList<>();

    public MultiSelectTypeAdapter(Context mContext, List list) {
        super(mContext, list);

    }

    /**
     * 设置选中的item
     *
     * @param position
     */
    public void setItemChecked(int position) {
        if (mSelectList!=null){
            mSelectList.add(position);
        }
    }

    /**
     * 设置未选中的item
     *
     * @param position
     */
    public void setItemUnChecked(Integer position) {
        if (mSelectList!=null){
            mSelectList.remove(position);
        }
    }


    // 2020/7/12 根据position得到item的选中状态
    /**
     * 获取item的选中状态
     * @param position
     * @return
     */
    public boolean getItemChecked(int position) {
        for (Integer integer:mSelectList) {
            if (position == integer){
                return true;
            }
        }
        return false;
    }


    /**
     * 获取选中的position
     * @return
     */
    public List<Integer> getSelectListIndex(){
        List<Integer> result = new ArrayList<>();
        result.addAll(mSelectList);
        return result;
    }


    @Override
    public void onBindViewHolder(BaseViewHolder holder, int type, Object data, int position) {
        if (type == VIEW_TYPE_NORMAL){
            bindData(holder,(T) data,position);
        }
    }

    /**
     * 绑定数据源
     * @param holder
     * @param data
     * @param position
     */
    public abstract void bindData(BaseViewHolder holder,T data, int position);
}
